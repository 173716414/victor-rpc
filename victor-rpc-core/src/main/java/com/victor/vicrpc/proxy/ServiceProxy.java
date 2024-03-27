package com.victor.vicrpc.proxy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.victor.vicrpc.RpcApplication;
import com.victor.vicrpc.config.RpcConfig;
import com.victor.vicrpc.constant.RpcConstant;
import com.victor.vicrpc.model.RpcRequest;
import com.victor.vicrpc.model.RpcResponse;
import com.victor.vicrpc.model.ServiceMetaInfo;
import com.victor.vicrpc.registry.Registry;
import com.victor.vicrpc.registry.RegistryFactory;
import com.victor.vicrpc.serializer.JdkSerializer;
import com.victor.vicrpc.serializer.Serializer;
import com.victor.vicrpc.serializer.SerializerFactory;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.proxy
 *Project：victor-rpc
 *name：ServiceProxy
 *Date：2024/3/15  14:33
 *Filename：ServiceProxy
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
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
            ServiceMetaInfo selectedServiceMetaInfo1 = serviceMetaInfoList.get(0);

        //     发送请求
            try (HttpResponse httpResponse = HttpRequest.post(selectedServiceMetaInfo1.getServiceAddress())
                         .body(bodyBytes)
                         .execute()) {
                byte[] result = httpResponse.bodyBytes();
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
