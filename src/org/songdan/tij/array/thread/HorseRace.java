package org.songdan.tij.array.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 赛马，练习CyclicBarries
 *
 * @author Songdan
 * @date 2016/4/18
 */
class Horse implements Runnable {

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
        return "Horse "+ id +" ";
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

public class HorseRace {

    static final int FINISH_LINE = 75;

    private List<Horse> horses = new ArrayList<>();

    private ExecutorService executors = Executors.newCachedThreadPool();

    private CyclicBarrier barrier ;

    public HorseRace(int nHorse,int pause) {
        barrier = new CyclicBarrier(nHorse, new Runnable() {

            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    sb.append("=");
                }
                System.out.println(sb.toString());
                //打印Horse的赛程
                for (Horse horse : horses) {
                    System.out.println(horse.tracks());
                }
                //如果有一匹马达到了终点，终止比赛
                for (Horse horse : horses) {
                    if (horse.getStrides() >= FINISH_LINE) {
                        System.out.println(horse+"win");
                        executors.shutdownNow();
                        return;
                    }
                }
            }
        });
        for (int i = 0; i < nHorse; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            executors.execute(horse);
        }
    }

    public static void main(String[] args) {
        new HorseRace(10, 10);
    }
}
