package com.ls.faw.netty.c1;
/**
 * @author banma-0148
 * @date 2023/02/25
 */

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author 李帅
 * @version $ Id: TestFileCopy, v 0.1 2023/02/25 11:15 banma-0148 Exp $
 */
public class TestFileCopy {

    public static void main(String[] args) throws IOException {
        String source = "D:\\a";
        String target = "D:\\b";
        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetName = path.toString().replace(source, target);
                // 是目录
                if (Files.isDirectory(path)) {
                    // D:\a\新建文件夹
                    // D:\b\新建文件夹
                    Files.createDirectory(Paths.get(targetName));
                }
                // 是普通文件
                else if (Files.isRegularFile(path)) {
                    Files.copy(path,Paths.get(targetName));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }

}