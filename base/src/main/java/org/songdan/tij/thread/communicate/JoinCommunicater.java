package org.songdan.tij.thread.communicate;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Songdan
 * @create: 2020-01-04 18:49
 **/
public class JoinCommunicater {

    private boolean work = false;

    private ReentrantLock lock = new ReentrantLock();



    public void communicate() {
        new Thread(()->{
            while (true) {

                System.out.println("begin working!!!");
            }
        });
        new Thread(()->{
            while (true) {
                try {
                    for (int i = 1; i <=10 ; i++) {
                        System.out.println("current num is " + i);
                        if (i == 5) {
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {

                    }
                }finally {
                }
            }

        }).start();
    }

}
