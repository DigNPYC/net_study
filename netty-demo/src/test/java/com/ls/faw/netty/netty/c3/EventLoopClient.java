package com.ls.faw.netty.netty.c3;
/**
 * @author banma-0148
 * @date 2023/02/27
 */

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author 李帅
 * @version $ Id: HelloClient, v 0.1 2023/02/27 16:24 banma-0148 Exp $
 */
@Slf4j
public class EventLoopClient {
    public static void main(String[] args) throws InterruptedException {
        // 2. 带有 Future，Promise 的类型都是和异步方法配套使用，用来处理结果
        ChannelFuture channelFuture = new Bootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override // 链接建立后被调用
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                // 1. 连接到服务器
                // 2. 异步非阻塞，main 发起调用，真正执行 connect 是 nio 线程
                .connect(new InetSocketAddress("localhost", 8080)); //1s 秒后
        // 2.1 使用 sync 方法同步处理结果
        channelFuture.sync(); // 阻塞住当前线程，直到nio线程链接建立完毕
        // 无阻塞的向下执行，获取 channel
        /*Channel channel = channelFuture.channel();
        log.debug("+++++++++++++++++++++++++++++{}",channel);
        channel.writeAndFlush("hello,world");*/

        //2.2 addListener（回调对象） 方法异步处理结果
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            // 在 nio 线程链接建立好之后，会调用 operationComplete
            public void operationComplete(ChannelFuture future) throws Exception {
                Channel channel = future.channel();
                log.debug("+++++++++++++++++++++++++++++{}",channel);
                channel.writeAndFlush("hello,world");
            }
        });
    }
}