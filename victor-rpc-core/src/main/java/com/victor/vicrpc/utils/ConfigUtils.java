package com.victor.vicrpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.utils
 *Project：victor-rpc
 *name：ConfigUtils
 *Date：2024/3/18  10:37
 *Filename：ConfigUtils
 */
public class ConfigUtils {

    public static <T> T loadConfig(Class<T> tClasss, String prefix) {
        return loadConfig(tClasss, prefix, "");
    }

    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            configFileBuilder.append("-").append(environment);
        }
        configFileBuilder.append(".properties");
        Props props = new Props(configFileBuilder.toString());
        return props.toBean(tClass, prefix);
    }
}
