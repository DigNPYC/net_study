package com.ls.faw.netty.c4;
/**
 * @author banma-0148
 * @date 2023/02/26
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author 李帅
 * @version $ Id: WriteClient, v 0.1 2023/02/26 10:29 banma-0148 Exp $
 */
public class WriteClient {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost",8080));

        // 3.接收数据
        int count = 0;
        while (true){
            ByteBuffer buffer = ByteBuffer.allocate(1024*1024);
            count +=sc.read(buffer);
            System.out.println(count);
            buffer.clear();
        }
    }
}