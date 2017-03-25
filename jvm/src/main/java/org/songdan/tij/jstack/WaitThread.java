package org.songdan.tij.jstack;

/**
 * Wait线程的堆栈
 * Created by SongDan on 2017/3/25.
 */
public class WaitThread {

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
        }, "wait_thread").start();
        new Thread(() -> {
            synchronized (lock) {//如果此处拿到的是lock2的锁，下面lock.notify会报错,而wait_thread会一直在等待
                //do something
                long workTime = System.currentTimeMillis() + 30 * 1000;
                while (System.currentTimeMillis()<workTime) {
                }
                lock.notify();
            }
        }, "notify_thread").start();

    }

}
