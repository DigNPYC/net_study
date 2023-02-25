package com.ls.faw.netty.c1;
/**
 * @author banma-0148
 * @date 2023/02/25
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author 李帅
 * @version $ Id: TestFileChannelTransferTo, v 0.1 2023/02/25 10:12 banma-0148 Exp $
 */
public class TestFileChannelTransferTo {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("netty-demo/data.txt").getChannel();
             FileChannel to = new FileOutputStream("netty-demo/to.txt").getChannel();
        ) {
            // 效率高，底层会用操作系统的零拷贝进行优化，只能传输 2G 数据
            long size = from.size();
            // left 代表还剩余多少字节
            for(long left = size; left>0; ){
                System.out.println("position:"+(size-left)+"left:"+left);
                left -= from.transferTo(size-left, size,to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}