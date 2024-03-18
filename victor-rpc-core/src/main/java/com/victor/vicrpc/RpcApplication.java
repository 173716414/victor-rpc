package com.victor.vicrpc;

import com.victor.vicrpc.config.RpcConfig;
import com.victor.vicrpc.constant.RpcConstant;
import com.victor.vicrpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc
 *Project：victor-rpc
 *name：RpcApplication
 *Date：2024/3/18  11:02
 *Filename：RpcApplication
 */

/**
 * RPC框架应用
 * 相当于holder，存放了项目全局用的变量。双检锁单例模式实现
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
    }

    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
