package org.songdan.tij.thread.threadlocal;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2019-02-25 21:26
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(2));
        for (int i = 0; i < 7; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        System.out.println(threadPoolExecutor.getActiveCount());
        TimeUnit.MILLISECONDS.sleep(10);
        System.out.println(threadPoolExecutor.getActiveCount());
    }

}
