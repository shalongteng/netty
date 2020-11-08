package com.slt.netty.socket;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 先启动server 后启动client
 *  这是同步式网络编程
 */
public class TcpServer {
    public static void main(String[] args) throws Exception{
        //监听 6666端口
        ServerSocket serverSocket = new ServerSocket(6666);
        //多个client 可以连接同一个server
        //accept一次 在服务器端 建立一个插座，跟client建立连接

        while (true){
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            //readUTF 阻塞读 会影响其他客户端连接，只有结束本次循环，才能accept
            System.out.println(dataInputStream.readUTF());
            dataInputStream.close();
            socket.close();

        }
    }
}
