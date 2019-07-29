package org.songdan.tij.thread.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2018-08-10 10:38
 **/
public class ExecutorErroDemo {

    public static void queueSize() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 5, 30, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10));
        for (int i = 0; i < 20; i++) {
            int finalI = i+1;
            executor.submit(() -> {
                try {
                    System.out.println("执行："+finalI);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ">>>>" + "task " + finalI);
            });
        }
        System.out.println("提交完成");
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("over");
    }

    public static void main(String[] args) {
        queueSize();
    }

}
