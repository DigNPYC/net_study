package com.ls.faw.netty.c4;
/**
 * @author banma-0148
 * @date 2023/02/26
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author 李帅
 * @version $ Id: WriteServer, v 0.1 2023/02/26 10:21 banma-0148 Exp $
 */
public class WriteServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ssc.bind(new InetSocketAddress(8080));

        while (true){
            selector.select();
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()){
                SelectionKey key = iter.next();
                iter.remove();
                if (key.isAcceptable()){
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector,0,null);
                    scKey.interestOps(SelectionKey.OP_READ);

                    // 1.向客户端发送大量数据
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < 50000000; i++) {
                        sb.append("a");
                    }
                    ByteBuffer buffer = Charset.defaultCharset().encode(sb.toString());
                    //  2.返回值代表实际写入的字节数
                    int write = sc.write(buffer);
                    System.out.println(write);

                    // 3.判断是否有剩余内容
                    if (buffer.hasRemaining()){
                        // 4.关注可写事件
                        scKey.interestOps(SelectionKey.OP_WRITE + scKey.interestOps());
                        // 5.把未写完的数据挂到 scKey 上
                        scKey.attach(buffer);
                    }
                } else if (key.isWritable()) {
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    SocketChannel sc = (SocketChannel) key.channel();
                    int write = sc.write(buffer);
                    System.out.println(write);

                    // 6.清理操作

                    if (!buffer.hasRemaining()){
                        key.attach(null);// 需要清除buffer
                        key.interestOps(key.interestOps() - SelectionKey.OP_WRITE);// 不需要再关注可写事件
                    }
                }
            }
        }
    }
}