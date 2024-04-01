package com.victor.vicrpc.config;

import com.victor.vicrpc.loadbalancer.LoadBalancerKeys;
import com.victor.vicrpc.serializer.SerializerKeys;
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

    private String loadBalancer = LoadBalancerKeys.ROUND_ROBIN;

    private String name = "vic-rpc";

    private String version = "1.0";

    private String serverHost = "localhost";

    private Integer serverPort = 8080;
    /**
     * 模拟调用
     */
    private boolean mock = false;
    /**
     * 序列化器
     */
    private String serializer = SerializerKeys.JDK;

    /**
     * 注册中心配置
     */
    private RegistryConfig registryConfig = new RegistryConfig();
}
