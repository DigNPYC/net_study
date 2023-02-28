package com.ls.faw.netty.netty.c3;
/**
 * @author banma-0148
 * @date 2023/02/28
 */

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author 李帅
 * @version $ Id: TestNettyFuture, v 0.1 2023/02/28 17:32 banma-0148 Exp $
 */
@Slf4j
public class TestNettyFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        Future<Integer> future = eventLoop.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("执行计算");
                Thread.sleep(1000);
                return 50;
            }
        });

        // 同步获取结果
//        log.debug("{}",future.getNow());
//        log.debug("等待结果");
//        log.debug("{}",future.get());

        // 异步获取结果
        future.addListener(new GenericFutureListener<Future<? super Integer>>() {
            @Override
            public void operationComplete(Future<? super Integer> future) throws Exception {
                log.debug("接收结果{}",future.getNow());
            }
        });
        group.shutdownGracefully();
    }
}