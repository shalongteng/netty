package com.slt.netty.io.nio.another;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * http://ifeve.com/java-nio-channel-to-channel/
 * 如果两个通道中有一个是FileChannel，那你可以直接将数据从一个channel传输到另外一个channel。
 */
public class TestChannel2 {
    public static void main(String[] args) throws Exception {
        String path = "G:\\git_workspace\\netty\\src\\main\\java\\com\\slt\\netty\\io\\nio\\another\\TestChannel.java";
        //随机流（RandomAccessFile）不属于IO流，支持对文件的读取和写入随机访问。
        RandomAccessFile fromFile = new RandomAccessFile(path, "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("toFile.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position,count);
    }
}
