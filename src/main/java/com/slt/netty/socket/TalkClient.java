package com.slt.netty.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 */
public class TalkClient {
    public static void main(String[] args) throws Exception{
        String s = null;
        //reader 可以读一行  读取用户输入的数据
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //读取客户端 传过来的数据
        InputStream inputStream = null;
        //写给客户端
        OutputStream outputStream = null;
        //建立连接
        Socket socket = new Socket("127.0.0.1",8888);
        while (true) {
            //客户端先开启聊天
            s = bufferedReader.readLine();
            outputStream = socket.getOutputStream();
//            new BufferedWriter(new OutputStreamWriter(outputStream)).write(s);
            new DataOutputStream(outputStream).writeUTF(s);

            inputStream = socket.getInputStream();
            String line = new DataInputStream(inputStream).readUTF();
            System.out.println("server:"+line);
        }
    }
}
