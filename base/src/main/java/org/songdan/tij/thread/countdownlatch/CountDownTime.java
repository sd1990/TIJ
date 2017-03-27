package org.songdan.tij.thread.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 使用倒计数锁存器实现 计算运行时间的功能
 *
 * @author Songdan
 * @date 2016/5/9
 */
public class CountDownTime {

    public static long time(Executor executor, int concurrency, final Runnable action) throws InterruptedException {
        //准备功能的倒计数锁存器
        final CountDownLatch ready = new CountDownLatch(concurrency);
        //工作
        final CountDownLatch start = new CountDownLatch(1);
        //结束的倒计数锁存器，为了任务完成的最终计时
        final CountDownLatch end = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    //开始倒计数
                    ready.countDown();
                    try {
                        //等待start倒计数结束
                        start.await();
                        action.run();
                    }
                    catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    finally {
                        end.countDown();
                    }
                }
            });
        }
        //阻塞，等待ready上的倒计数为0
        ready.await();
        //记录下时间，倒计数开始
        long startTime = System.nanoTime();
        //start倒计时
        start.countDown();
        //阻塞，等待end倒计数为0
        end.await();
        return System.nanoTime() - startTime;
    }

    public static void main(String[] args) throws InterruptedException {
        long time = time(Executors.newFixedThreadPool(5), 5, new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println(Thread.currentThread() + "--> jump :" + (i + 1));
                }
            }
        });
        System.out.println(time);
    }

}
