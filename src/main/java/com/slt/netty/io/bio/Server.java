package com.slt.netty.io.bio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统的服务器端BIO的经典编程模型：
 * bio+多线程
 *  bio阻塞读或者写的时候，使用多线程
 *  利用多核
 *
 * 正常不能throws 异常，需要try处理，正确管理连接。
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket();
        //监听8888端口
        ss.bind(new InetSocketAddress("127.0.0.1", 8888));
        while(true) {
            //阻塞方法
            Socket s = ss.accept();
            /**
             * 来一个客户端，服务器启动一个线程对其进行处理
             */
            new Thread(() -> {
                handle(s);
            }).start();
        }

    }

    static void handle(Socket s) {
        try {
            byte[] bytes = new byte[1024];
            //读也是阻塞
            int len = s.getInputStream().read(bytes);
            System.out.println("server"+new String(bytes, 0, len));
            //写也是阻塞
            s.getOutputStream().write(bytes, 0, len);
            s.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
