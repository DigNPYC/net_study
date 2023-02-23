package com.ls.faw.netty.c1;
/**
 * @author banma-0148
 * @date 2023/02/23
 */

import java.nio.ByteBuffer;

import static com.ls.faw.netty.c1.util.ByteBufferUtil.debugAll;

/**
 * @author 李帅
 * @version $ Id: TestBufferReadWrite, v 0.1 2023/02/23 21:19 banma-0148 Exp $
 */
public class TestBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(((byte) 0x61)); // a
        debugAll(buffer);
        buffer.put(new byte[]{0x62,0x63,0x64}); // b c d
        debugAll(buffer);
//        System.out.println(buffer.get());
        buffer.flip();
        System.out.println((char) buffer.get());
        debugAll(buffer);

        buffer.compact();
        debugAll(buffer);

        buffer.put(new byte[]{0x65,0x6f}); // b c d
        debugAll(buffer);
    }
}