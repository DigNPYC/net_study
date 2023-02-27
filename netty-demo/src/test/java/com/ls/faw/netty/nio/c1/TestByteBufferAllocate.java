package com.ls.faw.netty.nio.c1;
/**
 * @author banma-0148
 * @date 2023/02/23
 */

import java.nio.ByteBuffer;

/**
 * @author 李帅
 * @version $ Id: TestByteBufferAllocate, v 0.1 2023/02/23 21:36 banma-0148 Exp $
 */
public class TestByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());
        System.out.println(ByteBuffer.allocateDirect(16).getClass());

        /**
         * class java.nio.HeapByteBuffer        ----> Java 堆内存，读写效率低，受到 GC 的影响
         * class java.nio.DirectByteBuffer      ----> 直接（系统）内存，读写效率高（少一次拷贝），不会受GC影响，分配的效率低
         */


    }
}