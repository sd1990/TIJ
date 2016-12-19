package org.songdan.tij.classloader.execute;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * @author Songdan
 * @date 2016/11/3 11:15
 */
public class Test {

    public static void main(String[] args) throws IOException {

        InputStream fileInputStream = new FileInputStream("G:\\minework\\TIJ\\target\\classes\\org\\songdan\\tij\\atomic\\AtomicIntegerDemo.class");
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        fileInputStream.close();

        String execute = JavaClassExecutor.execute(bytes);
        System.out.println(execute);
    }

}
