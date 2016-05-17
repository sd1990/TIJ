package org.songdan.tij.array.thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 比较notify和notifyAll的功能
 * notify和notifyAll都是唤醒在当前锁上wait()的任务，notifyAll唤醒所有在当前锁上阻塞的任务
 * Created by PC on 2016/5/17.
 */
public class NotifyVsNotifyAll {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Task1());
        }
        executorService.execute(new Task2());
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {

            boolean prod = true;

            @Override
            public void run() {
                if (prod) {
                    System.out.println("\nnotify() ");
                    Task1.blocker.prod();
                } else{
                    System.out.println("\nnotifyAll() ");
                    Task1.blocker.prodAll();
                }
                prod = !prod;
            }
        },500,500);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("\nTimer canceled");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nTask2.blocker.prodAll() ");
        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nshutting down");
        executorService.shutdownNow();
    }
}

class Blocker {

    synchronized void run() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.print(Thread.currentThread() + " ");
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    synchronized void prod() {
        notify();
    }

    synchronized void prodAll() {
        notifyAll();
    }
}

class Task1 implements Runnable {

    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.run();
    }
}

class Task2 implements Runnable {

    static Blocker blocker = new Blocker();

    @Override
    public void run() {
        blocker.run();
    }

}