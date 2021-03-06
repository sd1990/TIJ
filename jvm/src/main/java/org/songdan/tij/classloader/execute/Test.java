package org.songdan.tij.classloader.execute;

import org.songdan.tij.classloader.deadlock.ErrorClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author Songdan
 * @date 2016/11/3 11:15
 */
public class Test {

    public static void main(String[] args) throws IOException {

//        InputStream fileInputStream = new FileInputStream("G:\\minework\\TIJ\\target\\classes\\org\\songdan\\tij\\atomic\\AtomicIntegerDemo.class");
//        byte[] bytes = new byte[fileInputStream.available()];
//        fileInputStream.read(bytes);
//        fileInputStream.close();
//
//        String execute = JavaClassExecutor.execute(bytes);
//        System.out.println(execute);
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    System.out.println(Thread.currentThread()+": start work!!!");
                    ErrorClass errorClass = new ErrorClass();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        latch.countDown();
    }

}
