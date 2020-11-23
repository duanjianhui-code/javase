package net.socketthread;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Duanjianhui
 * @create 2020-10-15 11:14
 */
public class ImageClient {
    public static void main(String[] args) {

        try {
            //建立socket服务
            Socket socket = new Socket(InetAddress.getLocalHost(), 9090);
            //建立socket输入流
            InputStream inputStream = socket.getInputStream();
            File file = new File("C:\\Users\\duanjianhui\\Desktop\\test1");
            if(!(file.exists())){
                file.mkdirs();
            }
            //建立文件输出流
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\duanjianhui\\Desktop\\test1\\3.zip");
            //边读边写
            byte[] buf=new byte[1024];
            int length=0;
            while ((length=inputStream.read(buf))!=-1){
                fileOutputStream.write(buf,0,length);
            }
            //关闭资源
            fileOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
