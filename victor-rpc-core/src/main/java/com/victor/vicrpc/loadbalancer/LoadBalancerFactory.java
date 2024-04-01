package com.victor.vicrpc.loadbalancer;

import com.victor.vicrpc.spi.SpiLoader;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.loadbalancer
 *Project：victor-rpc
 *name：LoadBalancerFactory
 *Date：2024/4/1  11:18
 *Filename：LoadBalancerFactory
 */
public class LoadBalancerFactory {
    static {
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     * 默认负载均衡器
     */
    public static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    /**
     * 获取实例
     * @param key 负载均衡器名称
     * @return 负载均衡器实例
     */
    public static  LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }
}
