package com.ls.faw.netty.netty.c4;
/**
 * @author banma-0148
 * @date 2023/03/02
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

import java.util.concurrent.TimeUnit;

import static com.ls.faw.netty.netty.c4.TestByteBuf.log;

/**
 * @author 李帅
 * @version $ Id: TestCompositeByteBuf, v 0.1 2023/03/02 15:05 banma-0148 Exp $
 */
public class TestCompositeByteBuf {
    public static void main(String[] args) {

        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.writeBytes(new byte[]{1,2,3,4,5});

        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf2.writeBytes(new byte[]{6,7,8,9,10});

        Long time1 = System.currentTimeMillis();

//        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
//        buffer.writeBytes(buf1).writeBytes(buf2);
//        log(buffer);

        CompositeByteBuf byteBufs = ByteBufAllocator.DEFAULT.compositeBuffer();
        byteBufs.addComponents(true,buf1,buf2);
        log(byteBufs);


        Long time = System.currentTimeMillis()-time1;
        System.out.println(time);
    }
}