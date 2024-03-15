package com.victor.vicrpc.serializer;

import java.io.*;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.serializer
 *Project：victor-rpc
 *name：JdkSerialize
 *Date：2024/3/15  10:00
 *Filename：JdkSerialize
 */
public class JdkSerialize implements Serializer{
    /**
     * 序列化
     *
     * @param object 输入对象
     * @return 序列化比特流
     * @param <T> 类型
     * @throws IOException io异常
     */
    @Override
    public <T> byte[] serialize(T object) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * 反序列化
     *
     * @param bytes 输入比特流
     * @param type 输出类型
     * @return
     * @param <T> 类型
     * @throws IOException io异常
     */
    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        try {
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            objectInputStream.close();
        }
    }
}
