package com.slt.netty.io.nio.another;

import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * http://ifeve.com/channels/
 * 使用FileChannel读取数据到Buffer中的示例：
 * buf.flip()，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据。
 */
public class TestChannel {
    public static void main(String[] args) throws Exception{
        String path = "G:\\git_workspace\\netty\\src\\main\\java\\com\\slt\\netty\\io\\nio\\another\\TestChannel.java";
        //随机流（RandomAccessFile）不属于IO流，支持对文件的读取和写入随机访问。
        RandomAccessFile aFile = new RandomAccessFile(path, "rw");
//        new FileOutputStream(path).getChannel()
        FileChannel inChannel = aFile.getChannel();
        //分配缓冲区
        ByteBuffer buf = ByteBuffer.allocate(48);
        //读取数据到Buffer
        int bytesRead = inChannel.read(buf);
        while (bytesRead != -1) {
            //反转Buffer 将postion 置到写之前的位置,从写模式切换到读模式
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }
            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();
    }
}
