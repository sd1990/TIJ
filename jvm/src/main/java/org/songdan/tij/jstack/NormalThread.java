package org.songdan.tij.jstack;

import java.util.concurrent.TimeUnit;

/**
 * 正常线程类的堆栈信息
 * Created by SongDan on 2017/3/25.
 */
public class NormalThread {

    public static void main(String[] args) {
        new Thread(() -> {
            while (1 == 1) {
                //空转
            }
        },"normal_thread").start();
    }

}
