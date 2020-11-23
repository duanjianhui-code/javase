package net.udp;

import java.io.IOException;
import java.net.*;

/**
 * @author Duanjianhui
 * @create 2020-10-12 18:45
 */
public class UdpSender {
    public static void main(String[] args) throws IOException {
        //建立udp的服务
        DatagramSocket datagramSocket=new DatagramSocket();
        //发送的内容
        String data="这是第一个udp例子";
        //建立数据包
        DatagramPacket packet = new DatagramPacket(data.getBytes(),data.getBytes().length, InetAddress.getLocalHost(),9090);
        //调用udp服务发送数据
        datagramSocket.send(packet);
        //关闭资源
        datagramSocket.close();
    }

}
