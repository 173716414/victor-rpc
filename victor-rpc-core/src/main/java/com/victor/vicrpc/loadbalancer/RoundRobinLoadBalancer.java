package com.victor.vicrpc.loadbalancer;

import com.victor.vicrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.loadbalancer
 *Project：victor-rpc
 *name：RoundRobinLoadBalancer
 *Date：2024/4/1  10:56
 *Filename：RoundRobinLoadBalancer
 */

/**
 * 轮询负载均衡器
 */
public class RoundRobinLoadBalancer implements LoadBalancer{

    /**
     * 当前轮询的下标
     */
    public static final AtomicInteger currentIndex = new AtomicInteger(0);
    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()) {
            return null;
        }

        int size = serviceMetaInfoList.size();
        // 只有一个，无需轮询
        if (size == 1) {
            return serviceMetaInfoList.get(0);
        }

        int index = currentIndex.getAndIncrement() % size;
        return serviceMetaInfoList.get(index);
    }
}
