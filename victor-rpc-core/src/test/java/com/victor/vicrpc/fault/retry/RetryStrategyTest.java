package com.victor.vicrpc.fault.retry;

import com.victor.vicrpc.model.RpcResponse;
import org.junit.Test;

/*
 *Author：Victor_htq
 *Package：com.victor.vicrpc.fault.retry
 *Project：victor-rpc
 *name：RetryStrategyTest
 *Date：2024/4/3  11:00
 *Filename：RetryStrategyTest
 */
public class RetryStrategyTest {
    RetryStrategy retryStrategy = new NoRetryStrategy();

    @Test
    public void doRetry() {
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(rpcResponse);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
}
