package com.victor.vicrpc.proxy;

import java.lang.reflect.Proxy;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.proxy
 *Project：victor-rpc
 *name：ServiceProxyFactory
 *Date：2024/3/15  14:38
 *Filename：ServiceProxyFactory
 */
public class ServiceProxyFactory {

    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy()
        );
    }
}
