package org.songdan.tij.thread.inherit;

import java.util.concurrent.TimeUnit;

/**
 * 父类的同步方法
 * @author Songdan
 * @date 2016/8/9
 */
public class SuperSynchronize {

    public synchronized void run(){
        System.out.println("super runing ...");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
