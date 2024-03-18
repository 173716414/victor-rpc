package com.victor.vicrpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.server.vicrpc.registry
 *Project：victor-rpc
 *name：LocalRegistry
 *Date：2024/3/15  9:28
 *Filename：LocalRegistry
 */
public class LocalRegistry {

    /**
     * 注册信息存储
     */
    public static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * 注册服务
     * @param serviceName 服务名称
     * @param implClass 实现类
     */
    public static void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }

    /**
     * 获取服务
     * @param serviceName 服务名称
     * @return class
     */
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * 删除服务
     * @param serviceName 服务名称
     */
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
