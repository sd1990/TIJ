package org.songdan.tij.thread.executors;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2018-12-13 10:31
 **/
public class ScheduleThreadPoolDemo {

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
        executor.prestartAllCoreThreads();
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(new Task(), 10, 10, TimeUnit.MILLISECONDS);
        scheduledFuture.cancel(false);
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread()+"start execute:" + currentSeconds());
                Thread.sleep(ThreadLocalRandom.current().nextInt(2));
                System.out.println(Thread.currentThread()+"end execute:"+currentSeconds());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static BigDecimal currentSeconds() {
        return BigDecimal.valueOf(System.currentTimeMillis()).divide(BigDecimal.valueOf(1000), 2, RoundingMode.HALF_UP);
    }


}
