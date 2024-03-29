package com.victor.vicrpc.protocol;

import lombok.Getter;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.protocol
 *Project：victor-rpc
 *name：ProtocolMessageStatusEnum
 *Date：2024/3/29  10:05
 *Filename：ProtocolMessageStatusEnum
 */
@Getter
public enum ProtocolMessageStatusEnum {

    OK("ok", 20),
    BAD_REQUEST("badRequest", 40),
    BAD_RESPONSE("badResponse", 50);

    private final String text;

    private final int value;

    // 私有构造函数
    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据value获取枚举
     * @param value value
     * @return 枚举
     */
    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum anEnum : ProtocolMessageStatusEnum.values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }
}
