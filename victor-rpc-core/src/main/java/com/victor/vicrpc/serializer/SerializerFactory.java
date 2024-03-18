package com.victor.vicrpc.serializer;

import com.victor.vicrpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.serializer
 *Project：victor-rpc
 *name：SerializerFactory
 *Date：2024/3/18  16:08
 *Filename：SerializerFactory
 */
public class SerializerFactory {

    /**
     * 序列化映射（用于实现单例）
     */
    // public static final Map<String, Serializer> KEY_SERIALIZER_MAP = new HashMap<>() {
    //     {
    //         put(SerializerKeys.JDK, new JdkSerializer());
    //         put(SerializerKeys.JSON, new JsonSerializer());
    //         put(SerializerKeys.KRYO, new KryoSerializer());
    //         put(SerializerKeys.HESSIAN, new HessianSerializer());
    //     }
    // };

    static {
        SpiLoader.load(Serializer.class);
    }
    /**
     * 默认序列化器
     */
    public static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
