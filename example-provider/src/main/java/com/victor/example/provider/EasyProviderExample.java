package com.victor.example.provider;

import com.victor.example.common.service.UserService;
import com.victor.vicrpc.registry.LocalRegistry;
import com.victor.vicrpc.server.VertxHttpServer;

/*
 *Author：Victor_htq
 *Package：com.victor.example.provider
 *Project：victor-rpc
 *name：EasyProviderExample
 *Date：2024/3/14  20:43
 *Filename：EasyProviderExample
 */
public class EasyProviderExample {

    public static void main(String[] args) {

        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
    //     提供web服务
        VertxHttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
