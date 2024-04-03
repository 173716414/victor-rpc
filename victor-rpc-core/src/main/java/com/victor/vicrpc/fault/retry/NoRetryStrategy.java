package com.victor.vicrpc.fault.retry;

import com.victor.vicrpc.model.RpcResponse;

import java.util.concurrent.Callable;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.fault.retry
 *Project：victor-rpc
 *name：NoRetryStrategy
 *Date：2024/4/3  10:55
 *Filename：NoRetryStrategy
 */

/**
 * 不重试机制
 */
public class NoRetryStrategy implements RetryStrategy{
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
