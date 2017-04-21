package org.songdan.tij.jstack;

/**
 * 死锁情况下的线程堆栈
 * Created by SongDan on 2017/3/25.
 */
public class DeadLockThread {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        new Thread(() -> {
            while (true) {
                synchronized (lock1) {
                    System.out.println("thread a get lock1");
                    //在这里等待10秒等待线程b获取到lock2
                    workTime();
                    synchronized (lock2) {
                        System.out.println("thread b get lock2");
                    }
                }
            }
        }, "thread-a").start();
        new Thread(() -> {
            while (true) {
                synchronized (lock2) {
                    System.out.println("thread b get lock2 ...");
                    workTime();
                    synchronized (lock1) {
                        System.out.println("thread b get lock1 ...");
                    }
                }
            }
        }, "thread-b").start();
    }

    public static void workTime() {
        long waitTime = System.currentTimeMillis() + 10 * 1000;
        while (System.currentTimeMillis() < waitTime) {

        }
    }

}
