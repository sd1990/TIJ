package org.songdan.tij.jstack;

/**
 * 空转时间类
 * Created by SongDan on 2017/3/25.
 */
public class WorkTime {

    public static void workTime(int seconds) {
        long waitTime = System.currentTimeMillis() + seconds * 1000;
        while (System.currentTimeMillis() < waitTime) {

        }
    }

}
