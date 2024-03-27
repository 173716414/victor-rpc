package com.victor.example.provider;

import com.victor.example.common.service.UserService;
import com.victor.vicrpc.RpcApplication;
import com.victor.vicrpc.config.RegistryConfig;
import com.victor.vicrpc.config.RpcConfig;
import com.victor.vicrpc.model.ServiceMetaInfo;
import com.victor.vicrpc.registry.LocalRegistry;
import com.victor.vicrpc.registry.Registry;
import com.victor.vicrpc.registry.RegistryFactory;
import com.victor.vicrpc.server.HttpServer;
import com.victor.vicrpc.server.VertxHttpServer;

/*
 *Author：Victor_htq
 *Package：com.victor.example.provider
 *Project：victor-rpc
 *name：ProviderExample
 *Date：2024/3/27  16:24
 *Filename：ProviderExample
 */
public class ProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceAddress(rpcConfig.getServerHost() + ":" + rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}

