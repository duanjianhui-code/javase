package net.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Duanjianhui
 * @create 2020-10-13 23:00
 */
public class ServerMain {
    public static void main(String[] args) throws IOException {
        //建立TCP服务端
        ServerSocket serverSocket = new ServerSocket(9090);
        //建立链接
        Socket socket = serverSocket.accept();
        //建立相应的输入流
        BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //获得输出流对象
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
        //键盘输入
        BufferedReader keybord=new BufferedReader(new InputStreamReader(System.in));
        String line=null;
        while ((line=bufferedReader.readLine())!=null){
            System.out.println("客户端发来的信息："+line);


            //发送数据给客户端
            System.out.println("请输入发给客户端的信息：");
            line=keybord.readLine();
            outputStreamWriter.write(line+"\r\n");
            outputStreamWriter.flush();
        }
        //关闭资源
        serverSocket.close();
    }
}
