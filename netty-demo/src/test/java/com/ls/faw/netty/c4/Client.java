package com.ls.faw.netty.c4;
/**
 * @author banma-0148
 * @date 2023/02/25
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author 李帅
 * @version $ Id: Client, v 0.1 2023/02/25 11:53 banma-0148 Exp $
 */
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost",8080));
        System.out.println("waiting...");
    }
}