package org.songdan.tij.thread.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 使用栅栏锁存器实现 计算运行时间的功能
 *
 * @author Songdan
 * @date 2016/5/9
 */
public class CountDownTimeWithCyclic {

    public static long time(final ExecutorService executor, int concurrency)
            throws BrokenBarrierException, InterruptedException {
        final List<Horse> horses = new ArrayList<>();
        final int count = 10;
        final long[] endTime = { 0L };
        final CountDownLatch end = new CountDownLatch(1);
        //准备功能的倒计数锁存器
        final CyclicBarrier ready = new CyclicBarrier(concurrency, new Runnable() {

            @Override
            public void run() {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    sb.append("=");
                }
                System.out.println(sb.toString());
                //打印Horse的赛程
                for (Horse horse : horses) {
                    System.out.println(horse.tracks());
                }
                for (Horse horse : horses) {
                    if (horse.getStrides() >= count) {
                        endTime[0] = System.nanoTime();
                        end.countDown();
                        executor.shutdownNow();
                    }
                }
            }
        });
        //开始时间
        long startTime = System.nanoTime();
        for (int i = 0; i < concurrency; i++) {
            Horse horse = new Horse(ready);
            horses.add(horse);
            executor.execute(horse);
        }
        end.await();
        return endTime[0] - startTime;
    }

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        long time = time(Executors.newFixedThreadPool(5), 5);
        System.out.println(time);
    }

}
