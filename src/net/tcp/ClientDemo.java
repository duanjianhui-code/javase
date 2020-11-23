package net.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Duanjianhui
 * @create 2020-10-13 20:12
 */
public class ClientDemo {
    public static void main(String[] args) throws IOException {
        //建立TCP服务
        Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
        //输出流对象
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("服务端你好".getBytes());
        //接收服务端发来的消息
        InputStream inputStream = socket.getInputStream();
        byte[] buf=new byte[1024];
        int length=inputStream.read(buf);
        System.out.println("服务端发来的消息："+new String(buf,0,length));
        //关闭资源
        socket.close();
    }
}
