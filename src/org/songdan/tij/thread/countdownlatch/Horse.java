package org.songdan.tij.thread.countdownlatch;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 赛马中的骂
 * Created by PC on 2016/9/11.
 */
public class Horse implements Runnable {

    private static int counter = 0;

    private final int id = ++counter;

    private CyclicBarrier cyclicBarrier;

    private static Random random = new Random();

    private int strides = 0;

    public Horse(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    public int getStrides() {
        return strides;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += random.nextInt(3);
                    cyclicBarrier.await();
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println("exit...");
        }
        catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            sb.append("*");
        }
        sb.append(id);
        return sb.toString();
    }
}
