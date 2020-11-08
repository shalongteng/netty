package com.slt.netty.socket;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @ProjectName:
 * 发送long值
 */
public class UdpClient {
    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        //将long类型值800 写到了字节数组里
        dataOutputStream.writeLong(800);

        byte[] buf = byteArrayOutputStream.toByteArray();
        //把字节数组 扔到包袱里
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length,new InetSocketAddress("127.0.0.1",6666));


        DatagramSocket datagramSocket = new DatagramSocket(9999);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
