package com.victor.example.consumer;

import com.victor.vicrpc.config.RpcConfig;
import com.victor.vicrpc.utils.ConfigUtils;

/*
 *Author：Victor_htq
 *Package：com.victor.example.consumer
 *Project：victor-rpc
 *name：ConsumerExample
 *Date：2024/3/18  11:18
 *Filename：ConsumerExample
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
