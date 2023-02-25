package com.ls.faw.netty.c1;
/**
 * @author banma-0148
 * @date 2023/02/24
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static util.ByteBufferUtil.debugAll;

/**
 * @author 李帅
 * @version $ Id: TestScatteringReads, v 0.1 2023/02/24 16:07 banma-0148 Exp $
 */
public class TestScatteringReads {
    public static void main(String[] args) {
        try (FileChannel channel = new RandomAccessFile("netty-demo/data.txt","r").getChannel()) {
            ByteBuffer b1 = ByteBuffer.allocate(3);
            ByteBuffer b2 = ByteBuffer.allocate(3);
            ByteBuffer b3 = ByteBuffer.allocate(5);
            channel.read(new ByteBuffer[]{b1,b2,b3});
            b1.flip();
            b2.flip();
            b3.flip();
            debugAll(b1);
            debugAll(b2);
            debugAll(b3);
        } catch (IOException e) {
        }
    }
}