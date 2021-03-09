package org.songdan.tij.classloader.deadlock;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Songdan
 * @create: 2020-12-27 22:04
 **/
public class DeadLockTest {

    public static void main(String[] args) {
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
