package net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * @author Duanjianhui
 * @create 2020-10-12 20:57
 */
public class ReceiveChat extends Thread {
    @Override
    public void run() {

        try {
            //建立udp服务
            DatagramSocket datagramSocket=new DatagramSocket(9090);
            byte[] buf=new byte[1024];
            DatagramPacket datagramPacket=new DatagramPacket(buf,buf.length);
            boolean flog=true;
            while (flog){
                datagramSocket.receive(datagramPacket);
                System.out.println(datagramPacket.getAddress().getHostName()+"说："+new String(buf,0,datagramPacket.getLength()));
            }
            datagramSocket.close();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
