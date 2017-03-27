package org.songdan.tij.thread.executors;

import org.songdan.tij.thread.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPool 一次性预先执行代价高昂的线程分配，不会滥用资源
 *
 * @author Songdan
 * @date 2016/5/11
 */
public class FixedThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }

}
