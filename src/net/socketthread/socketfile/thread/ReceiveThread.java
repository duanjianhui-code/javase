package net.socketthread.socketfile.thread;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Duanjianhui
 * @create 2020-10-17 17:28
 */
public class ReceiveThread extends Thread {
    Socket socket;
    public static int step;
    public Map<Integer, String> map = new HashMap<>();


    public ReceiveThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //tcp输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //建立TCP输出流
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            new Send(socket).start();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (step == 0) {
                    System.out.println("客户端发来信息：" + line);
                    if ("ok".equals(line)) {

                    } else if ("我要下载文件".equals(line)) {
                        File file = new File("C:\\Users\\duanjianhui\\Desktop\\test");
                        File[] files = file.listFiles();
                        int i = 1;
                        for (File file1 : files
                        ) {
                            String name = file1.getName();
                            map.put(i++, name);
                            outputStreamWriter.write("file：" + name + "\n");
                            outputStreamWriter.flush();
                        }
                        outputStreamWriter.write("over\n");
                        outputStreamWriter.flush();
                        step++;
                    }
                } else if (step!=0){
                    int index = 0;
                    if (map.containsKey((index = Integer.parseInt(line)))) {
                        String fileName = map.get(index);
                        File file = new File("C:\\Users\\duanjianhui\\Desktop\\test\\" + fileName);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        System.out.println("客户端要下载的文件为：" + fileName);
                        byte[] buf = new byte[1024];
                        int length = 0;
                        outputStreamWriter.write("fileDownload:" + fileName + "\n");
                        outputStreamWriter.flush();
                        while ((length = fileInputStream.read(buf)) != -1) {
                            outputStream.write(buf, 0, length);
                        }
                        fileInputStream.close();
                    }
                    step = 0;
                }
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
