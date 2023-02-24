package com.ls.faw.netty.c1;
/**
 * @author banma-0148
 * @date 2023/02/24
 */

import java.nio.ByteBuffer;

import static util.ByteBufferUtil.debugAll;

/**
 * @author 李帅
 * @version $ Id: TestBufferRead, v 0.1 2023/02/24 15:36 banma-0148 Exp $
 */
public class TestBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[] {'a','b','c','d'});
        buffer.flip();

        //从头开始读
//        buffer.get(new byte[4]);
//        debugAll(buffer);
//        buffer.rewind();
//        System.out.println((char) buffer.get());

        //mark & reset
        //mark 做一个标记，记录 position 位置， reset 是将 position 重置到 mark 的位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.mark();//加标记，索引为 2 的位置
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());
//        buffer.reset();//将 position 重置到索引 2
//        System.out.println((char) buffer.get());
//        System.out.println((char) buffer.get());

        //get(1),不会改变读索引的位置
        System.out.println((char) buffer.get(3));
        debugAll(buffer);


    }
}