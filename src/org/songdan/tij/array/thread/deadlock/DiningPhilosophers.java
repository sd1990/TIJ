package org.songdan.tij.array.thread.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by PC on 2016/5/21.
 */
public class DiningPhilosophers {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[5];
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new Chopstick();
        }
        for (int i = 0; i < 5; i++) {
            if (i<4) {
                executorService.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % 5], i + 1));
            }else{
                executorService.execute(new Philosopher(chopsticks[0],chopsticks[i],i+1));
            }
        }
    }
}
