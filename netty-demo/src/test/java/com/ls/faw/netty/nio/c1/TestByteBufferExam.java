package com.ls.faw.netty.nio.c1;
/**
 * @author banma-0148
 * @date 2023/02/24
 */

import java.nio.ByteBuffer;

import static util.ByteBufferUtil.debugAll;

/**
 * @author 李帅
 * @version $ Id: TestByteBufferExam, v 0.1 2023/02/24 16:27 banma-0148 Exp $
 */
public class TestByteBufferExam {
    public static void main(String[] args) {
        /**
         网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
         但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
             Hello,world\n
             I'm zhangsan\n
             How are you?\n
         变成了下面的两个 byteBuffer (黏包，半包)
             Hello,world\nI'm zhangsan\nHo
             w are you?\n
         现在要求你编写程序，将错乱的数据恢复成原始的按 \n 分隔的数据
         **/
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("Hello,woeld\nI'm zhangsan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source){
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n'){
                int length = i + 1 - source.position();
                //把这条完整消息存入新的 ByteBuffer
                ByteBuffer target = ByteBuffer.allocate(length);
                //从 source 读，向 target 写
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }
}