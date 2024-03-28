package com.victor.vicrpc.registry;


import com.victor.vicrpc.model.ServiceMetaInfo;

import java.util.List;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.registry
 *Project：victor-rpc
 *name：RegistryServiceCache
 *Date：2024/3/28  15:52
 *Filename：RegistryServiceCache
 */
public class RegistryServiceCache {

    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    /**
     *
     * @param newServiceCache
     */
    void writeCache(List<ServiceMetaInfo> newServiceCache) {
        this.serviceCache = newServiceCache;
    }

    /**
     * 读缓存
     * @return List<ServiceMetaInfo>
     */
    List<ServiceMetaInfo> readCache() {
        return this.serviceCache;
    }

    /**
     * 清空缓存
     */
    void clearCache() {
        this.serviceCache = null;
    }
}
