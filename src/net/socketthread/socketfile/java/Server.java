package net.socketthread.socketfile.java;

import net.socketthread.socketfile.thread.ReceiveThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Duanjianhui
 * @create 2020-10-17 17:22
 */
public class Server {
    public static void main(String[] args) {
        //创建服务器tcp服务
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            while (true){
                Socket socket = serverSocket.accept();
                new ReceiveThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
