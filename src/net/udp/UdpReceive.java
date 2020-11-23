package net.udp;



import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Duanjianhui
 * @create 2020-10-12 18:59
 */
public class UdpReceive {
    public static void main(String[] args) throws IOException {
        //建立udp服务，并绑定端口
        DatagramSocket datagramSocket = new DatagramSocket(9090);
        //准备空数组接受数据
        byte[] buf=new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
        //接收数据
        datagramSocket.receive(datagramPacket);
        System.out.println("接受的数据为"+new String(buf,0,datagramPacket.getLength()));
        datagramSocket.close();
    }
}
