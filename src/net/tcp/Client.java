package net.tcp;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Duanjianhui
 * @create 2020-10-14 10:49
 */
public class Client {
    public static void main(String[] args) {
        //创建Client tcp 服务
        try {
            Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 9090);

            RThread rThread = new RThread(socket);
            rThread.start();
            System.out.println("请输入名字：");
            Scanner scanner = new Scanner(System.in);
            String next = scanner.next();
            System.out.println("开始聊天");
            //创建输出流
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            //创建输入流
            BufferedReader keybord = new BufferedReader(new InputStreamReader(System.in));
            String line = null;
            while ((line = keybord.readLine()) != null) {
                outputStreamWriter.write(next + "----" + line + "\r\n");
                outputStreamWriter.flush();
                if ("bye".equals(line)) {
                    socket.close();
                    rThread.setRun(false);
                    System.exit(0);
                }

            }
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class RThread extends Thread {
        public Socket socket;
        public boolean isRun = true;

        public boolean isRun() {
            return isRun;
        }

        public void setRun(boolean run) {
            isRun = run;
        }

        public RThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (isRun) {
                try {
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    System.out.println(dataInputStream.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
