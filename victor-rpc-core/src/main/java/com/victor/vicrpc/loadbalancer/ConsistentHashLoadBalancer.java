package com.victor.vicrpc.loadbalancer;

import com.victor.vicrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

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
public class ConsistentHashLoadBalancer implements LoadBalancer{

    /**
     * 一致性Hash环，存放虚拟节点
     */
    private final TreeMap<Integer, ServiceMetaInfo> virtualNodes = new TreeMap<>();


    /**
     * 虚拟节点数
     */
    public static final int VIRTUAL_NODE_NUM = 100;

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

        // 构建虚拟节点环
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                int hash = getHash(serviceMetaInfo.getServiceAddress() + "#" + i);
                virtualNodes.put(hash, serviceMetaInfo);
            }
        }

        // 获取请求的 Hash 值
        int hash = getHash(requestParams);

        // 选择最接近且大于等于调用请求的 Hash 值的虚拟节点
        Map.Entry<Integer, ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hash);
        if (entry == null) {
            entry = virtualNodes.firstEntry();
        }
        return entry.getValue();
    }

    /**
     * hash算法
     * @param key 输入对象
     * @return hash值
     */
    private int getHash(Object key) {
        return key.hashCode();
    }
}
