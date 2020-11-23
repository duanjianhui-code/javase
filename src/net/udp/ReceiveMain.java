package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Duanjianhui
 * @create 2020-10-13 8:41
 */
public class ReceiveMain {
    public static void main(String[] args) {
        try {
            DatagramSocket datagramSocket = new DatagramSocket(9090);
            byte[] buf=new byte[1024];
            boolean flag=true;
            while (flag){
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(datagramPacket);
                System.out.println(InetAddress.getLocalHost().getHostAddress()+"说："+new String(buf,0,datagramPacket.getLength()));
            }
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
