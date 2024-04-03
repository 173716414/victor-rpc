package com.victor.vicrpc.fault.retry;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.fault.retry
 *Project：victor-rpc
 *name：RetryStrategyKeys
 *Date：2024/4/3  11:21
 *Filename：RetryStrategyKeys
 */
public interface RetryStrategyKeys {
    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定时间间隔
     */
    String FIXED_INTERVAL = "fixedInterval";
}
