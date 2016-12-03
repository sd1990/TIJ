package org.songdan.tij.classloader.initlization;

/**
 * 验证多线程同时加载一个类时，会有锁
 *
 * @author Songdan
 * @date 2016/10/25 9:40
 */
public class DeadLoopClass {
    static {

        if (true) {
            System.out.println(Thread.currentThread()+" init DeadLoopClass ...");
            while (true) {

            }
        }

    }

    public static void main(String[] args) {
        Runnable task = new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass deadLoopClass = new DeadLoopClass();
                System.out.println(Thread.currentThread() + "run over");
            }
        };
        new Thread(task).start();

        System.out.println(Thread.currentThread() + " run main");
    }
}
