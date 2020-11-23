package net.socketthread.socketfile.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @author Duanjianhui
 * @create 2020-10-18 16:32
 */
public class Download extends Thread {
    Socket socket;
    String fileName;

    public Download(Socket socket, String fileName) {
        this.socket = socket;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        File file = new File("C:\\Users\\duanjianhui\\Desktop\\test1\\" + fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);

            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = socket.getInputStream().read(buf, 0, 1024)) != -1) {
                fileOutputStream.write(buf, 0, length);
                if (length < 1024) {
                    break;
                }
            }
            String hostAddress = socket.getInetAddress().getHostAddress();
            System.out.println(hostAddress + "成功下载文件：" + fileName + "，大小为：" + file.length() / 1024 + "KB");
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
