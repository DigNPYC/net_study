package com.ls.faw.netty.netty.c3;
/**
 * @author banma-0148
 * @date 2023/03/01
 */

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

/**
 * @author 李帅
 * @version $ Id: TestNettyPromise, v 0.1 2023/03/01 16:56 banma-0148 Exp $
 */
@Slf4j
public class TestNettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.准备EventLoop对象
        EventLoop eventLoop = new NioEventLoopGroup().next();
        // 2.可以主动创建 promise,结果容器
        DefaultPromise<Integer> promise = new DefaultPromise<>(eventLoop);

        new Thread(()->{
            // 3.任意一个线程执行计算，计算完毕后向 promise 填充结果
            log.debug("开始计算...");
            try {
                int a = 1/0;
                Thread.sleep(1000);
                promise.setSuccess(80);
            } catch (Exception e) {
                e.printStackTrace();
                promise.setFailure(e);
            }

        }).start();

        // 4. 接收结果
        log.debug("等待结果。。。");
        log.debug("结果是：{}",promise.get());
    }
}