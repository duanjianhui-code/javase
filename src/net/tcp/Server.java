package net.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Duanjianhui
 * @create 2020-10-14 10:25
 */
public class Server {

    public static final Server INSTANCE = new Server();
    public Map<Integer, Socket> map = new HashMap<>();
    public static int i;


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=null;
        try {
            //创建Serversocket服务
            serverSocket = new ServerSocket(9090);
            while (true) {
                Socket socket = serverSocket.accept();
                new AccpetThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            serverSocket.close();
        }
    }

    static class AccpetThread extends Thread {

        public Socket socket;
        public boolean isRun = true;

        public AccpetThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (isRun) {
                //创建输入流
                try {
                    Server.INSTANCE.map.put(i++, socket);
                    InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println("客户端发来的消息：" + line);
                        if("bye".contains(line)){
                            Set<Integer> integers = Server.INSTANCE.map.keySet();
                            for (Integer integer : integers) {
                                if(socket.equals(Server.INSTANCE.map.get(integer))){
                                    Server.INSTANCE.map.remove(integer);
                                }
                            }
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;


                        }
                        send(line);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void send(String line) throws IOException {
            Set<Integer> integers = Server.INSTANCE.map.keySet();
            for (Integer integer : integers) {
                DataOutputStream dataOutputStream = new DataOutputStream(Server.INSTANCE.map.get(integer).getOutputStream());
                dataOutputStream.writeUTF(line); 
            }


        }
    }
}
