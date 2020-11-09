package com.slt.netty.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 一个简单的聊天服务器
 */
public class TalkServer {
    public static void main(String[] args) throws Exception{
        String s = null;
        //reader 可以读一行  读取用户输入的数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //读取客户端 传过来的数据
        InputStream inputStream = null;
        //写给客户端
        OutputStream outputStream = null;
        //监听8888端口
        ServerSocket serverSocket = new ServerSocket(8888);
        //建立连接 阻塞等待连接
        Socket socket = serverSocket.accept();
        System.out.println("a client connect");
        while (true) {

            //服务端接受 信息
            inputStream = socket.getInputStream();
            String line =new DataInputStream(inputStream).readUTF();
//            String line = new BufferedReader(new InputStreamReader(inputStream)).readLine();
            System.out.println("client:"+line);

            //服务端 返回信息
            s = bufferedReader.readLine();
            outputStream = socket.getOutputStream();
            new DataOutputStream(outputStream).writeUTF(s);
        }

    }
}
