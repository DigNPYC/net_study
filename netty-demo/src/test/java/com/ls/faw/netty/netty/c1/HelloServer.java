package com.ls.faw.netty.netty.c1;
/**
 * @author banma-0148
 * @date 2023/02/27
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author 李帅
 * @version $ Id: HelloServer, v 0.1 2023/02/27 15:56 banma-0148 Exp $
 */
public class HelloServer {
    public static void main(String[] args) {

        // 1.启动器，负责组装 netty 组件，启动服务器
        new ServerBootstrap()
                // 2. BossEventLoop , WorkerEventLoop(selector,thread) , group 组
                .group(new NioEventLoopGroup())
                // 3. 选择服务器的 ServerSocketChannel 实现
                .channel(NioServerSocketChannel.class)
                // boss 负责处理链接 worker(child) 负责处理读写，决定了 worker(child) 能执行哪些操作(handler)
                .childHandler(
                        //5. 代表和客户端进行读写的通道 Initializer 初始化，负责添加别的 handler
                    new ChannelInitializer<NioSocketChannel>() {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception {
                            // 6. 添加具体 handler
                            ch.pipeline().addLast(new StringDecoder()); // 将 ByteBuf 转换为字符串
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { // 自定义 handler
                                @Override // 读事件
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    // 打印上一步转换好的字符串
                                    System.out.println(msg);
                                }
                            });
                        }
                    })
                // 7.绑定监听端口
                .bind(8080);
    }
}