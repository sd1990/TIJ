package org.songdan.tij.thread.lockdemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by PC on 2016/7/7.
 */
public class LockDemo1 {

    static class InterrupedWait{

        private static Lock lock = new ReentrantLock();

        public static void lock() {
            boolean b = lock.tryLock();
            System.out.println(Thread.currentThread()+"after try lock");
            if (b) {
                lock.unlock();
            }
        }
        public static void lockInterrupted() throws InterruptedException {
            boolean b = lock.tryLock(2,TimeUnit.SECONDS);
            System.out.println(Thread.currentThread()+"after try lock,result :"+b);
            TimeUnit.SECONDS.sleep(3);
            if (b) {
                lock.unlock();
            }
        }


    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            executorService.submit(new Runnable() {

                @Override
                public void run() {
                    try {
                        InterrupedWait.lockInterrupted();
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
        TimeUnit.SECONDS.sleep(30);
        executorService.shutdownNow();
    }

}
