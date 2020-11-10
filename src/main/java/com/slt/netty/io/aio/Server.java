package com.slt.netty.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * 单线程
 * 本质上这是一个observer模式
 * 当有一个客户端连接的时候，操作系统回调CompletionHandler的completed方法，进行处理。
 */
public class Server {
    public static void main(String[] args) throws Exception {
        //插座的面板
        final AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(8888));

        //accept 不在阻塞了
        serverChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            //completed已经连接上了
            @Override
            public void completed(AsynchronousSocketChannel client, Object attachment) {
                //不写这行 下一个客户端连接不上  递归
                serverChannel.accept(null, this);
                try {
                    System.out.println(client.getRemoteAddress());
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    //非阻塞读  CompletionHandler读完之后的回调
                    client.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            System.out.println(new String(attachment.array(), 0, result));
                            client.write(ByteBuffer.wrap("HelloClient".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {
                            exc.printStackTrace();
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void failed(Throwable exc, Object attachment) {
                exc.printStackTrace();
            }
        });
        //防止main线程退出
        while (true) {
            Thread.sleep(1000);
        }
    }
}
