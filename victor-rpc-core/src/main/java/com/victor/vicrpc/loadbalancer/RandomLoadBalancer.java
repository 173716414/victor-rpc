package com.victor.vicrpc.loadbalancer;

import com.victor.vicrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;
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
public class RandomLoadBalancer implements LoadBalancer{

    private final Random random = new Random();

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

        return serviceMetaInfoList.get(random.nextInt(size));
    }
}
