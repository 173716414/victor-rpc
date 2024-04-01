package com.victor.vicrpc.loadbalancer;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.loadbalancer
 *Project：victor-rpc
 *name：LoadBalancerKeys
 *Date：2024/4/1  11:16
 *Filename：LoadBalancerKeys
 */
public interface LoadBalancerKeys {
    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";

    /**
     * 随机
     */
    String RANDOM = "random";

    /**
     * 一致性 Hash
     */
    String CONSISTENT_HASH = "consistentHash";
}
