package com.ls.faw.netty.c1;
/**
 * @author banma-0148
 * @date 2023/02/23
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;

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

        try(FileChannel channel = new FileOutputStream("netty-demo/data.txt",true).getChannel();){
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}