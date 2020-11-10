package com.slt.netty.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Channel 全双工
 * nio单线程模型
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
        ssc.configureBlocking(false);

        System.out.println("server started, listening on :" + ssc.getLocalAddress());
        //大管家
        Selector selector = Selector.open();
        //selector 对客户端连接 感兴趣，一单有一个客户端连接上了，会放到set里
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        //轮询
        while(true) {
            //阻塞方法 必须有一件事发生了
            selector.select();
            //调用selector的selectedKeys()方法，访问已选择键集中的就绪通道
            //selector 会往每一个插座扔一个监听器（key），事件（在这里是客户端连上了）发生了，会扔到set里
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while(it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                handle(key);
            }
        }

    }

    private static void handle(SelectionKey key) {
        //如果发现连接就绪，就建立这个通道
        if(key.isAcceptable()) {
            try {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                //将通道注册到selector 对读事件感兴趣
                sc.register(key.selector(), SelectionKey.OP_READ );
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
            //如果发现通道可读，处理此事件
        } else if (key.isReadable()) { //flip
            SocketChannel sc = null;
            try {
                // 返回该SelectionKey对应的channel。
                sc = (SocketChannel)key.channel();
                ByteBuffer buffer = ByteBuffer.allocate(512);
                buffer.clear();
                int len = sc.read(buffer);

                if(len != -1) {
                    System.out.println(new String(buffer.array(), 0, len));
                }
                //将字节数组包装到缓冲区中。
                ByteBuffer bufferToWrite = ByteBuffer.wrap("HelloClient".getBytes());
                sc.write(bufferToWrite);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                //关闭通道
                if(sc != null) {
                    try {
                        sc.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
