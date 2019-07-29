package org.songdan.tij.thread.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * @author: Songdan
 * @create: 2019-07-14 16:19
 **/
public class RwL {

    ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private List<Integer> list = new ArrayList<>();


    public void testDowngrading() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(() -> {
            readWriteLock.writeLock().lock();
            //锁降级操作
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " write lock down grade!!!");
            countDownLatch.countDown();
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            readWriteLock.writeLock().unlock();

        }).start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        new Thread(() -> {
            //锁降级操作
            long s = System.currentTimeMillis();
            readWriteLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " get read lock cost : " + (System.currentTimeMillis() - s));
            readWriteLock.readLock().unlock();

        }).start();

    }

    public static void main(String[] args) {
        new RwL().testDowngrading();
    }
}
