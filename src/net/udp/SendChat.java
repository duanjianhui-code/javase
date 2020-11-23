package net.udp;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Duanjianhui
 * @create 2020-10-12 20:33
 */
public class SendChat extends Thread {
    @Override
    public void run() {
        //建立udp服务
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            //键盘输入
            BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            DatagramPacket datagramPacket=null;
            while ((line = keyReader.readLine()) != null) {
                datagramPacket = new DatagramPacket(line.getBytes(), line.getBytes().length, InetAddress.getByName("127.0.0.255"), 9090);
                datagramSocket.send(datagramPacket);
            }
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
