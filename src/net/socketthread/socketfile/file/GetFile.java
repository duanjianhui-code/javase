package net.socketthread.socketfile.file;

import org.junit.Test;

import java.io.File;

/**
 * @author Duanjianhui
 * @create 2020-10-17 18:36
 */

public class GetFile extends Thread {
    @Test
    @Override
    public void run() {
        StringBuffer stringBuffer = new StringBuffer();
        File file = new File("C:\\Users\\duanjianhui\\Desktop\\test");
        File[] files = file.listFiles();
        for (File file1:files
             ) {
            String name=file1.getName();
            stringBuffer.append(name+"\n");
        }
        System.out.println(stringBuffer);
    }
}
