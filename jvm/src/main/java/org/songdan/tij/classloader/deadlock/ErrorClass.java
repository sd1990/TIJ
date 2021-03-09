package org.songdan.tij.classloader.deadlock;

import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2020-12-27 22:02
 **/
public class ErrorClass {

    static{
        System.out.println(Thread.currentThread() + ":cinit work");
        while (true) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public ErrorClass() {
        System.out.println("new instance");
    }
}
