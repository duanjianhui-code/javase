package net.udp;

/**
 * @author Duanjianhui
 * @create 2020-10-12 21:07
 */
public class ChatMain {
    public static void main(String[] args) {
        ReceiveChat receiveChat = new ReceiveChat();
        receiveChat.start();
        SendChat sendChat = new SendChat();
        sendChat.start();
       /* ChatReceive chatReceive = new ChatReceive();
        chatReceive.start();
        ChatSender chatSender = new ChatSender();
        chatSender.start();*/
    }
}
