package com.victor.vicrpc.fault.retry;

import com.victor.vicrpc.serializer.JdkSerializer;
import com.victor.vicrpc.serializer.Serializer;
import com.victor.vicrpc.spi.SpiLoader;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.serializer
 *Project：victor-rpc
 *name：SerializerFactory
 *Date：2024/3/18  16:08
 *Filename：SerializerFactory
 */
public class RetryStrategyFactory {

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
        SpiLoader.load(RetryStrategy.class);
    }
    /**
     * 默认序列化器
     */
    public static final RetryStrategy DEFAULT_RETRY_STRATEGY = new NoRetryStrategy();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static RetryStrategy getInstance(String key) {
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }
}
