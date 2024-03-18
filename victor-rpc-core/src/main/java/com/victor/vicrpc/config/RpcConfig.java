package com.victor.vicrpc.config;

import lombok.Data;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.config
 *Project：victor-rpc
 *name：RpcConfig
 *Date：2024/3/18  10:35
 *Filename：RpcConfig
 */
@Data
public class RpcConfig {

    private String name = "vic-rpc";

    private String version = "1.0";

    private String serverHost = "localhost";

    private Integer serverPort = 8080;
    /**
     * 模拟调用
     */
    private boolean mock = false;
}
