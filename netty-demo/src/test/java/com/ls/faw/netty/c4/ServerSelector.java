package com.ls.faw.netty.c4;
/**
 * @author banma-0148
 * @date 2023/02/25
 */

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static util.ByteBufferUtil.debugRead;

/**
 * @author 李帅
 * @version $ Id: Server, v 0.1 2023/02/25 11:39 banma-0148 Exp $
 */

@Slf4j
public class ServerSelector {
    public static void main(String[] args) throws IOException {

        // 1.创建selector,管理多个 channel
        Selector selector = Selector.open();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(8080));
        ssc.configureBlocking(false);

        // 2.建立 selector 和 channel 的联系（channel）
        // SelectionKey 就是将来事件发生后，通过它可以知道事件和那个 channel 的事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);
        log.debug("register key :{}",sscKey);

        while (true) {

            // 3.select 方法，没有事件发生，线程阻塞；有事件，线程恢复运行
            // select 在事件未处理是，它不会阻塞，事件发生后要么处理，要么取消，不能置之不理
            selector.select();

            //4. 处理事件，selectedKeys 内部包含了所有发生的事件
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()){
                SelectionKey key = iter.next();
                // 处理 key 时，要从 selectedKeys 集合中删除，否则下次处理就会有问题
                iter.remove();
                log.debug("key:{}",key);
                // 5.区分事件类型
                if (key.isAcceptable()){ // 如果是 accept 事件
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);
                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.debug("{}",sc);
                } else if (key.isReadable()) {
                    SocketChannel channel = (SocketChannel) key.channel();// 拿到触发事件的 channel
                    channel.read(buffer);
                    buffer.flip();
                    debugRead(buffer);
                }
//                key.cancel();
            }
        }
    }
}