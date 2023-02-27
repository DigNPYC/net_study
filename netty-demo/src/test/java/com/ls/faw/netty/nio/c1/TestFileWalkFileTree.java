package com.ls.faw.netty.nio.c1;
/**
 * @author banma-0148
 * @date 2023/02/25
 */

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 李帅
 * @version $ Id: TestFileWalkFileTree, v 0.1 2023/02/25 10:35 banma-0148 Exp $
 */
public class TestFileWalkFileTree {
    public static void main(String[] args) throws IOException {

//        Files.delete(Paths.get("D:\\新建文件夹"));

        Files.walkFileTree(Paths.get("D:\\新建文件夹"),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("=========>进入"+dir);
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                Files.delete(file);
                return super.visitFile(file, attrs);
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("=========>推出"+dir);
                Files.delete(dir);
                return super.postVisitDirectory(dir, exc);
            }
        });
    }

    private static void m2() throws IOException {
        AtomicInteger txtCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:\\Software\\AliYuQue"),new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if(file.toString().endsWith(".txt")){
                    System.out.println(file);
                    txtCount.incrementAndGet();
                }
                return super.visitFile(file, attrs);
            }
        });
        System.out.println(txtCount);
    }

    private static void m1() throws IOException {
        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();
        Files.walkFileTree(Paths.get("D:\\Software\\AliYuQue"),new SimpleFileVisitor<Path>(){


            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("======>"+dir);
                dirCount.incrementAndGet();
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println(file);
                fileCount.incrementAndGet();
                return super.visitFile(file, attrs);
            }
        });
        System.out.println("dir count:"+dirCount);
        System.out.println("file count:"+fileCount);
    }
}