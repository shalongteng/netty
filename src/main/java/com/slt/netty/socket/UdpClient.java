package com.slt.netty.socket;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * @ProjectName: netty
 */
public class UdpClient {
    public static void main(String[] args) throws Exception {
        byte[] buf = new String("hello").getBytes();
        //把字节数组 扔到包袱里
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length,new InetSocketAddress("127.0.0.1",6666));


        DatagramSocket datagramSocket = new DatagramSocket(9999);
        datagramSocket.send(datagramPacket);
        datagramSocket.close();
    }
}
