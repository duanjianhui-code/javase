package net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Duanjianhui
 * @create 2020-10-13 22:47
 */
public class ClientMain {
    public static void main(String[] args) throws IOException {
        //建立TCP服务
        Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
        //建立输出流
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        //建立输入流
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //从键盘输入
        BufferedReader keyReader = new BufferedReader(new InputStreamReader(System.in));
        String line=null;
        while ((line=keyReader.readLine())!=null){
            outputStreamWriter.write(line+"\r\n");
            outputStreamWriter.flush();
           //服务器返回的数据
            line=bufferedReader.readLine();
            System.out.println("服务器发来信息："+line);
            System.out.println("请输入发给服务端的信息：");
        }
        //关闭资源
        socket.close();
    }
}
