package org.songdan.tij.thread.communicate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁通信
 *
 * @author: Songdan
 * @create: 2020-01-04 18:37
 **/
public class LockCommunicater {

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition condition = reentrantLock.newCondition();

    private Condition aCondition = reentrantLock.newCondition();

    private Condition bCondition = reentrantLock.newCondition();

    private boolean work = false;

    public void communicate() {
        new Thread(()->{
            while (true) {
                reentrantLock.lock();
                try {
                    while (!work) {
                        try {
                            aCondition.await();
                        } catch (InterruptedException e) {

                        }
                    }
                    System.out.println("begin working!!!");
                    work = false;
                    bCondition.signal();
                }finally {
                    reentrantLock.unlock();
                }
            }
        }).start();
        new Thread(()->{
            while (true) {
                reentrantLock.lock();
                try {
                    for (int i = 1; i <=10 ; i++) {
                        System.out.println("current num is " + i);
                        if (i == 5) {
                            try {
                                work = true;
                                aCondition.signal();
                                bCondition.await();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {

                    }
                }finally {
                    reentrantLock.unlock();
                }
            }

        }).start();
    }

    public static void main(String[] args) {
        new LockCommunicater().communicate();
    }

}
