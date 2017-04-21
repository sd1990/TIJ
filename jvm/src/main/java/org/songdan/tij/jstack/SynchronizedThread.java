package org.songdan.tij.jstack;

/**
 * 两个线程互斥情况下的线程堆栈
 * Created by SongDan on 2017/3/25.
 */
public class SynchronizedThread {

    public static void main(String[] args) {
        Object lock = new Object();
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    System.out.println("thread a working ...");
                }
            }
        }, "thread-a").start();
        new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    System.out.println("thread b working ...");
                }
            }
        }, "thread-b").start();
    }

}
