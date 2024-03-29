package com.victor.vicrpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.protocal
 *Project：victor-rpc
 *name：ProtocalMessage
 *Date：2024/3/29  9:44
 *Filename：ProtocalMessage
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolMessage<T> {
    /**
     * 消息头
     */
    private Header header;

    /**
     * 消息体（请求或响应对象）
     */
    private T body;

    @Data
    public static class Header {
        /**
         * 魔数，保证安全性
         */
        private byte magic;

        /**
         * 版本号
         */
        private byte version;

        /**
         * 序列化器
         */
        private byte serializer;

        /**
         * 消息类型（请求/响应）
         */
        private byte type;

        /**
         * 状态
         */
        private byte status;

        /**
         * 请求id
         */
        private long requestId;

        /**
         * 消息体长度
         */
        private int bodyLength;
    }
}
