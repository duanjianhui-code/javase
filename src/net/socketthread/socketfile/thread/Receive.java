package net.socketthread.socketfile.thread;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Duanjianhui
 * @create 2020-10-17 20:21
 */
public class Receive extends Thread {
    public Socket socket;
    public boolean isRun = true;
    public static int step=0;
    public Map<Integer, String> map = new HashMap<>();


    public Map<Integer, String> getMap() {
        return map;
    }

    public void setMap(Map<Integer, String> map) {
        this.map = map;
    }

    public Receive(Socket socket) {
        this.socket = socket;
    }

    public boolean isRun() {
        return isRun;
    }

    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void run() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (isRun) {
            try {
                //tcp输入流
                String line = null;
                int i = 1;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line.startsWith("file：")) {
                        map.put(i++, line);
                    } else if ("over".equals(line)) {
                        System.out.println("************文件列表如下************");
                        Set<Integer> integers = map.keySet();
                        for (Integer integer : integers) {
                            System.out.println("编号" + integer + ":" + map.get(integer));
                        }
                        map.clear();
                        i=1;
                        System.out.println("请选择你要下载的文件编号");
                    } else if (line.startsWith("fileDownload")) {
                        String[] split = line.split(":");
                        String fileName=split[1];
                        File file = new File("C:\\Users\\duanjianhui\\Desktop\\test1\\" + fileName);
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        byte[] buf=new byte[1024];
                        int length=0;
                        while ((length=socket.getInputStream().read(buf,0,1024))!=-1){

                            fileOutputStream.write(buf,0,length);
                            if(length<1024){
                                    break;
                            }
                        }
                        String hostAddress = socket.getInetAddress().getHostAddress();
                        System.out.println(hostAddress+"成功下载文件："+fileName+"，大小为："+(file.length()/1024>1024?file.length()/1024/1024+"MB":file.length()/1024+"KB"));
                        fileOutputStream.close();
                    } else {
                        System.out.println("服务端发来信息：》》》" + line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
