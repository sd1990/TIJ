package org.songdan.tij.thread.countdownlatch;

import java.util.concurrent.*;

/**
 * @author Songdan
 * @date 2016/5/13
 */
public class CountDownTime2 {

    public static long time(ExecutorService executor, int current, final Runnable action) throws InterruptedException {
        final CountDownLatch start = new CountDownLatch(current);
        final CountDownLatch work = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(current);
        for (int i = 0; i < current; i++) {
            executor.execute(new Runnable() {

                @Override
                public void run() {
                    //开始的时候报到
                    start.countDown();
                    try {
                        work.await();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    action.run();
                    //执行结束的时候报到
                    end.countDown();
                }
            });
        }
        start.await();
        //记录开始时间
        long startTime = System.currentTimeMillis();
        work.countDown();//由于计数器为1，此操作通知阻塞在此计数器上的线程激活
        end.await();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(time(Executors.newCachedThreadPool(), 5, new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : hello world");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }));
    }
}
