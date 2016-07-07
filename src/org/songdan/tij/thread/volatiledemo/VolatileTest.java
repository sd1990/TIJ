package org.songdan.tij.thread.volatiledemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 验证volatile只能保证可见性，不能保证原子性
 * Created by PC on 2016/7/7.
 */
public class VolatileTest {

    private static volatile int race = 0;

    private static void increat() {
        race++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        final CountDownLatch countDownLatch = new CountDownLatch(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increat();
                    }
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println(race);
        executorService.shutdownNow();
    }

}
