package com.victor.vicrpc.serializer;

import java.io.IOException;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc
 *Project：victor-rpc
 *name：Serializer
 *Date：2024/3/15  9:42
 *Filename：Serializer
 */
public interface Serializer {

    /**
     * 序列化
     * @param object 输入对象
     * @return
     * @param <T> 类型
     * @throws IOException io异常
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 序列化
     * @param bytes 输入比特流
     * @param type 输出类型
     * @return
     * @param <T> 类型
     * @throws IOException
     */
    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
