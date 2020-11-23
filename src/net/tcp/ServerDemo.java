package net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Duanjianhui
 * @create 2020-10-13 20:18
 */
public class ServerDemo {
    public static void main(String[] args) throws IOException {
        //建立TCP服务服务端,监听一个端口
        ServerSocket serverSocket = new ServerSocket(9090);

        /**
         * 接收客户端链接
         * accept()是一个阻塞方法·
         */
        Socket socket = serverSocket.accept();
        //获得输入流
        InputStream inputStream = socket.getInputStream();
        byte[] buf=new byte[1024];
        int length=inputStream.read(buf);
        System.out.println("服务端接收的数据为："+new String(buf,0,length));
        //返回数据给客户端
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("客户端你好".getBytes());
        //关闭资源
        serverSocket.close();
    }
}
