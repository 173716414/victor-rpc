package com.victor.vicrpc.protocol;

import cn.hutool.core.util.ObjectUtil;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.protocol
 *Project：victor-rpc
 *name：ProtocolMessageSerializerEnum
 *Date：2024/3/29  10:20
 *Filename：ProtocolMessageSerializerEnum
 */
@Getter
public enum ProtocolMessageSerializerEnum {

    JDK(0, "jdk"),
    JSON(1, "json"),
    KRYO(2, "kryo"),
    HESSIAN(3, "hessian");

    private final int key;

    private final String value;

    ProtocolMessageSerializerEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 获取值列表
     * @return 枚举值列表
     */
    public static List<String> getValues() {
        return Arrays.stream(values())
                .map(item -> item.value)
                .collect(Collectors.toList());
    }

    /**
     * 根据key获取枚举
     * @param key key
     * @return 枚举
     */
    public static ProtocolMessageSerializerEnum getEnumByKey(int key) {
        for (ProtocolMessageSerializerEnum anEnum : ProtocolMessageSerializerEnum.values()) {
            if (anEnum.key == key) {
                return anEnum;
            }
        }
        return null;
    }

    /**
     * 根据value获取枚举
     * @param value value
     * @return 枚举
     */
    public static ProtocolMessageSerializerEnum getEnumByValue(String value) {
        if (ObjectUtil.isEmpty(value)) {
            return null;
        }
        for (ProtocolMessageSerializerEnum anEnum : ProtocolMessageSerializerEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }
}
