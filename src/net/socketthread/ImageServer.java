package net.socketthread;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

/**
 * @author Duanjianhui
 * @create 2020-10-14 22:10
 */
public class ImageServer extends Thread{
    public  Socket socket;
    //使用该集合用来存储ip地址
    static HashSet<String> ips=new HashSet<String>();
    public ImageServer(Socket socket) {
        this.socket=socket;
    }
    @Override
    public void run() {
        try {
            //获取socket的输出流
            OutputStream outputStream=socket.getOutputStream();
            File file = new File("C:\\Users\\duanjianhui\\Desktop\\test");
            File[] files = file.listFiles();
            //获取图片输入流
            FileInputStream fileInputStream=null;
            for (File file1 : files) {
                String filePath=file1.getPath();
                fileInputStream = new FileInputStream(filePath);
                byte[] buf=new byte[1024];
                int leight=0;
                while ((leight=fileInputStream.read(buf))!=-1){
                    outputStream.write(buf,0,leight);
                }
            }

            String address = socket.getInetAddress().getHostAddress();
            if (ips.add(address)){
                System.out.println("恭喜ip为："+address+"成功下载，当前下载的人数为："+ips.size());
            }
            //关闭资源
            fileInputStream.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        //建立TCP服务
        ServerSocket serverSocket = new ServerSocket(9090);
        while (true){
            //进行连接
            Socket socket = serverSocket.accept();
            new ImageServer(socket).start();
            
        }
    }
}
