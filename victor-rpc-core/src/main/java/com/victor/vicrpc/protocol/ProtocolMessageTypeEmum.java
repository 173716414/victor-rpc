package com.victor.vicrpc.protocol;

import lombok.Getter;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.protocol
 *Project：victor-rpc
 *name：ProtocolMessageTypeEmum
 *Date：2024/3/29  10:15
 *Filename：ProtocolMessageTypeEmum
 */
@Getter
public enum ProtocolMessageTypeEmum {

    REQUEST(0),
    RESPONSE(1),
    HEART_BEAT(2),
    OTHERS(3);

    private final int key;

    ProtocolMessageTypeEmum(int key) {
        this.key = key;
    }

    /**
     * 根据key获取枚举
     * @param key key
     * @return enum
     */
    public static ProtocolMessageTypeEmum getEnumByKey(int key) {
        for (ProtocolMessageTypeEmum anEnum : ProtocolMessageTypeEmum.values()) {
            if (anEnum.key == key) {
                return anEnum;
            }
        }
        return null;
    }
}
