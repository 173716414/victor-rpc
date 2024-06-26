package com.victor.vicrpc.proxy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.victor.vicrpc.RpcApplication;
import com.victor.vicrpc.config.RpcConfig;
import com.victor.vicrpc.constant.RpcConstant;
import com.victor.vicrpc.fault.retry.RetryStrategy;
import com.victor.vicrpc.fault.retry.RetryStrategyFactory;
import com.victor.vicrpc.loadbalancer.LoadBalancer;
import com.victor.vicrpc.loadbalancer.LoadBalancerFactory;
import com.victor.vicrpc.model.RpcRequest;
import com.victor.vicrpc.model.RpcResponse;
import com.victor.vicrpc.model.ServiceMetaInfo;
import com.victor.vicrpc.protocol.*;
import com.victor.vicrpc.registry.Registry;
import com.victor.vicrpc.registry.RegistryFactory;
import com.victor.vicrpc.serializer.JdkSerializer;
import com.victor.vicrpc.serializer.Serializer;
import com.victor.vicrpc.serializer.SerializerFactory;
import com.victor.vicrpc.server.tcp.VertxTcpClient;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.proxy
 *Project：victor-rpc
 *name：ServiceProxy
 *Date：2024/3/15  14:33
 *Filename：ServiceProxy
 */
public class ServiceProxy implements InvocationHandler {
    /**
     * 调用代理
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 构造请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }

            // 负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            // 将调用方法名（请求路径）作为负载均衡参数
            HashMap<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);

            RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
            // 发送 TCP 请求
            RpcResponse rpcResponse = retryStrategy.doRetry(() ->
                    VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo));
            return rpcResponse.getData();

            //     发送请求
            //     try (HttpResponse httpResponse = HttpRequest.post(selectedServiceMetaInfo.getServiceAddress())
            //                  .body(bodyBytes)
            //                  .execute()) {
            //         byte[] result = httpResponse.bodyBytes();
            //         RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            //         return rpcResponse.getData();
            //     }
            // } catch (IOException e) {
            //     e.printStackTrace();
            // }

            // 发送TCP请求
            // Vertx vertx = Vertx.vertx();
            // NetClient netClient = vertx.createNetClient();
            // CompletableFuture<RpcResponse> responseFuture = new CompletableFuture<>();
            // netClient.connect(selectedServiceMetaInfo.getServicePort(),
            //         selectedServiceMetaInfo.getServiceHost(),
            //         result -> {
            //             if (result.succeeded()) {
            //                 System.out.println("Connected to TCP server");
            //                 NetSocket socket = result.result();
            //                 // 构造消息
            //                 ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
            //                 ProtocolMessage.Header header = new ProtocolMessage.Header();
            //                 header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
            //                 header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
            //                 header.setSerializer((byte) ProtocolMessageSerializerEnum.getEnumByValue(RpcApplication.getRpcConfig().getSerializer()).getKey());
            //                 header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
            //                 header.setRequestId(IdUtil.getSnowflakeNextId());
            //                 protocolMessage.setHeader(header);
            //                 protocolMessage.setBody(rpcRequest);
            //                 // 编码请求
            //                 try {
            //                     Buffer encodeBuffer = ProtocolMessageEncoder.encode(protocolMessage);
            //                     socket.write(encodeBuffer);
            //                 } catch (IOException e) {
            //                     throw new RuntimeException("协议消息编码错误");
            //                 }
            //
            //                 socket.handler(buffer -> {
            //                     try {
            //                         ProtocolMessage<RpcResponse> rpcResponseProtocolMessage = (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decode(buffer);
            //                     } catch (IOException e) {
            //                         throw new RuntimeException("协议消息解码错误");
            //                     }
            //                 });
            //             } else {
            //                 System.err.println("Failed to connect to TCP server");
            //             }
            //         });
            // RpcResponse rpcResponse = responseFuture.get();
            // // 关闭连接
            // netClient.close();
            // return rpcResponse.getData();


        } catch (IOException e) {
            throw new RuntimeException("调用失败");
        }
    }
}
