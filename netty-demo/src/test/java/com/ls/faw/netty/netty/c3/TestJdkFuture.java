package com.ls.faw.netty.netty.c3;
/**
 * @author banma-0148
 * @date 2023/02/28
 */

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * @author 李帅
 * @version $ Id: TestJdkFuture, v 0.1 2023/02/28 17:08 banma-0148 Exp $
 */

@Slf4j
public class TestJdkFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1. 线程池
        ExecutorService service = Executors.newFixedThreadPool(2);
        // 2. 提交任务
        Future<Integer> future = service.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("执行计算");
                Thread.sleep(1000);
                return 50;
            }
        });

        // 3. 通过 future 来获取结果
        log.debug("等待结果");
        log.debug("结果是 {}", future.get());
    }
}