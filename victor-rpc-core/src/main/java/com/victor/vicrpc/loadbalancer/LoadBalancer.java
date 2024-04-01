package com.victor.vicrpc.loadbalancer;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.loadbalancer
 *Project：victor-rpc
 *name：LoadBalancer
 *Date：2024/4/1  10:53
 *Filename：LoadBalancer
 */

import com.victor.vicrpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * 负载均衡器
 */
public interface LoadBalancer {

    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);
}
