package com.ls.faw.netty.nio.c1;
/**
 * @author banma-0148
 * @date 2023/02/24
 */

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static util.ByteBufferUtil.debugAll;

/**
 * @author 李帅
 * @version $ Id: TestByteBufferString, v 0.1 2023/02/24 15:51 banma-0148 Exp $
 */
public class TestByteBufferString {
    public static void main(String[] args) {

        //1.字符串转换为 ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        buffer.put("hello".getBytes());
        debugAll(buffer);

        //2.Charset
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("hello");
        debugAll(buffer1);

        //3.wrap
        ByteBuffer buffer2 = ByteBuffer.wrap("hello".getBytes());
        debugAll(buffer2);

        //buffer还原字符串
        CharBuffer str1 = StandardCharsets.UTF_8.decode(buffer1);
        System.out.println(str1);

        buffer.flip();
        CharBuffer str2 = StandardCharsets.UTF_8.decode(buffer);
        System.out.println(str2);


    }
}