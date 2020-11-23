package net.socketthread.socketfile.java;

import net.socketthread.socketfile.thread.Receive;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Duanjianhui
 * @create 2020-10-17 17:22
 */
public class Client {
    public static void main(String[] args) {
        try {
            //建立TCP服务
            Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
            //建立TCP输出流
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            //键盘输入
            BufferedReader keybord = new BufferedReader(new InputStreamReader(System.in));
            Receive receive = new Receive(socket);
            receive.start();
            String line=null;
            while ((line=keybord.readLine())!=null){
                outputStreamWriter.write(line+"\r\n");
                outputStreamWriter.flush();
                if ("bye".equals(line)) {
                    socket.close();
                    receive.setRun(false);
                    socket.close();
                    System.exit(0);
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
