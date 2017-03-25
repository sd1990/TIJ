package org.songdan.tij.jstack;

import java.util.concurrent.TimeUnit;

/**
 * 睡眠线程的堆栈
 * Created by SongDan on 2017/3/25.
 */
public class SleepThread {

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(30);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"sleep_thread").start();
    }

}
