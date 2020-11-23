package net.socketthread.socketfile.thread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * @author Duanjianhui
 * @create 2020-10-17 20:32
 */
public class Send extends Thread {
    Socket socket;

    public Send(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        try {
            //键盘输入
            BufferedReader keybord = new BufferedReader(new InputStreamReader(System.in));
            //建立TCP输出流
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            String line=null;
            while ((line=keybord.readLine())!=null){
                outputStreamWriter.write(line+"\r\n");
                outputStreamWriter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
