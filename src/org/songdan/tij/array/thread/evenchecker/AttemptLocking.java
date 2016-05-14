package org.songdan.tij.array.thread.evenchecker;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 练习Lock的灵活方法
 * Created by PC on 2016/5/14.
 */
public class AttemptLocking {

    private Lock lock = new ReentrantLock();

    public void untimed() {
        boolean capture = lock.tryLock();
        try {
            System.out.println("tryLock(): "+capture);
        }finally {
            if (capture) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean capture = false;
        try {
            capture = lock.tryLock(3, TimeUnit.SECONDS);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("lock.tryLock(3, TimeUnit.SECONDS):"+capture);
        }
        finally {
            if (capture) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        final AttemptLocking attemptLocking = new AttemptLocking();
        attemptLocking.untimed();
        attemptLocking.timed();
        new Thread(){

            @Override
            public void run() {
                attemptLocking.lock.lock();
                /*try {
                    TimeUnit.SECONDS.sleep(4);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    attemptLocking.lock.unlock();
                }*/
            }
        }.start();
        try {
            TimeUnit.SECONDS.sleep(1);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        attemptLocking.untimed();
        attemptLocking.timed();

    }

}
