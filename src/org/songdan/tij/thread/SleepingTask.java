package org.songdan.tij.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Songdan
 * @date 2016/5/11
 */
public class SleepingTask extends LiftOff {

    @Override
    public void run() {
        while (countDown-- != 0) {
            System.out.println(status());
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new SleepingTask());
        }
        executorService.shutdown();
    }
}
