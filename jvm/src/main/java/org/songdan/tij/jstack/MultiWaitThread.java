package org.songdan.tij.jstack;

/**
 * Wait线程的堆栈
 * Created by SongDan on 2017/3/25.
 */
public class MultiWaitThread {

    public static void main(String[] args) {
        Object lock = new Object();
//        Object lock2 = new Object();
        new Thread(() -> {
            synchronized (lock) {
                try {
//                    lock.wait();
                    lock.wait(30*1000);
                    System.out.println("我被唤醒了");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "wait_thread_1").start();
        new Thread(() -> {
            synchronized (lock) {//如果此处拿到的是lock2的锁，下面lock.notify会报错,而wait_thread会一直在等待
                //do something
                WorkTime.workTime(30);
                lock.notify();
            }
        }, "notify_thread").start();
        WorkTime.workTime(3);
        new Thread(() -> {
            synchronized (lock) {
                try {
                    //                    lock.wait();
                    lock.wait(30*1000);
                    System.out.println("我被唤醒了");
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "wait_thread_2").start();

    }

}
