package com.victor.vicrpc.fault.retry;

import com.victor.vicrpc.model.RpcResponse;

import java.util.concurrent.Callable;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.fault.retry
 *Project：victor-rpc
 *name：RetryStrategy
 *Date：2024/4/3  10:51
 *Filename：RetryStrategy
 */

/**
 * 重试策略接口
 */
public interface RetryStrategy {

    /**
     * 重试
     * @param callable RpcResponse
     * @return RpcResponse
     * @throws Exception e
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
