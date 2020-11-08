package com.slt.netty.socket;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @ProjectName: netty
 * 接收long值
 */
public class UdpServer {
    public static void main(String[] args) throws Exception {
        byte[] buf = new byte[1024];
        //包裹 用来接收对方发过来的数据
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
        DatagramSocket datagramSocket = new DatagramSocket(6666);
        while (true){
            //使用 包袱 初始化 字节数组流
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buf);
            DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
            //接收数据 扔到包袱中去   阻塞等待
            datagramSocket.receive(datagramPacket);
            System.out.println(dataInputStream.readLong());
        }
    }
}
