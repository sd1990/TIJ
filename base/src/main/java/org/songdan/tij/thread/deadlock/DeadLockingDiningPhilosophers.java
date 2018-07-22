package org.songdan.tij.thread.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 哲学家进餐问题死锁版
 * Created by PC on 2016/5/21.
 */
public class DeadLockingDiningPhilosophers {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[5];
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < 5; i++) {
            executorService.execute(new Philosopher(chopsticks[i], chopsticks[(i + 4) % 5], ThreadLocalRandom.current().nextInt(100,200)));
        }
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdownNow();
    }
}
