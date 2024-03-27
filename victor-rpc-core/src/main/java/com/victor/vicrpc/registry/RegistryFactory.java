package com.victor.vicrpc.registry;

import com.victor.vicrpc.spi.SpiLoader;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.registry
 *Project：victor-rpc
 *name：RegistryFactory
 *Date：2024/3/27  15:04
 *Filename：RegistryFactory
 */
public class RegistryFactory {

    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    public static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
