package net.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Duanjianhui
 * @create 2020-10-13 8:30
 */
public class SendMain {
    public static void main(String[] args) {
        //建立udp服务
        try {
            DatagramSocket datagramSocket = new DatagramSocket();
            //从键盘输入流
            BufferedReader keybord = new BufferedReader(new InputStreamReader(System.in));
            String line=null;
            while ((line=keybord.readLine())!=null){
                DatagramPacket datagramPacket = new DatagramPacket(line.getBytes(),line.getBytes().length, InetAddress.getByName("127.0.0.255"),9090);
                datagramSocket.send(datagramPacket);
            }
            datagramSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
