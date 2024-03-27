package com.victor.vicrpc.config;

import lombok.Data;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.config
 *Project：victor-rpc
 *name：RegistryConfig
 *Date：2024/3/27  11:16
 *Filename：RegistryConfig
 */
@Data
public class RegistryConfig {

    /**
     * 注册中心类别
     */
    private String registry = "etcd";

    /**
     * 注册中心地址
     */
    private String address = "http://localhost:2380";

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间（ms）
     */
    private Long timeout = 10000L;
}
