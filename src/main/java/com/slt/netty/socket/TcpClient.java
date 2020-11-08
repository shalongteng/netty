package com.slt.netty.socket;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 在建立连接以后，在连接的管道里有两根管道。
 *  一根输入管道
 *  一根输出管道
 */
public class TcpClient {
    public static void main(String[] args) throws Exception{
        //client 端口系统随机分配一个
        Socket socket = new Socket("127.0.0.1",6666);

        //通过管道 通过流 通信
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF("hello server");

        dataOutputStream.flush();
        dataOutputStream.close();
        socket.close();
    }
}
