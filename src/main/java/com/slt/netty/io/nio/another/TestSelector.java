package com.slt.netty.io.nio.another;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * 与Selector一起使用时，Channel必须处于非阻塞模式下。
 * FileChannel与Selector一起使用，因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
 */
public class TestSelector {
    public static void main(String[] args) throws Exception{
        //创建一个selector
        Selector selector = Selector.open();

        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.socket().bind(new InetSocketAddress("127.0.0.1", 8888));
        //与Selector一起使用时，Channel必须处于非阻塞模式下。
        channel.configureBlocking(false);
        /**
         * 向Selector注册通道
         * register()第二个参数。是一个“interest集合”，通过Selector监听Channel时对什么事件感兴趣。
         *  Connect
         *  Accept
         *  Read
         *  Write
         */
        SelectionKey selectionKey = channel.register(selector,SelectionKey.OP_READ);
        /**
         * 通道触发了一个事件意思是该事件已经就绪。
         * 可读的通道可以说是“读就绪”。
         * 等待写数据的通道可以说是“写就绪”。
         */

        //调用selector的selectedKeys()方法，访问“已选择键集中的就绪通道
        Set selectedKeys = selector.selectedKeys();

        //可以遍历这个已选择的键集合来访问就绪的通道。
        Iterator keyIterator = selectedKeys.iterator();
        while(keyIterator.hasNext()) {
            SelectionKey key = (SelectionKey) keyIterator.next();
            if(key.isAcceptable()) {
                // a connection was accepted by a ServerSocketChannel.
            } else if (key.isConnectable()) {
                // a connection was established with a remote server.
            } else if (key.isReadable()) {
                // a channel is ready for reading
            } else if (key.isWritable()) {
                // a channel is ready for writing
            }
            keyIterator.remove();
        }
    }
}
