package com.victor.vicrpc.protocol;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.protocal
 *Project：victor-rpc
 *name：ProtocolConstant
 *Date：2024/3/29  9:52
 *Filename：ProtocolConstant
 */
public interface ProtocolConstant {

    /**
     * 消息头长度
     */
    int MESSAGE_HEADER_LENGTH = 17;

    /**
     * 协议魔数
     */
    byte PROTOCOL_MAGIC = 0x1;

    /**
     * 协议版本号
     */
    byte PROTOCOL_VERSION = 0x1;
}
