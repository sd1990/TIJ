package org.songdan.tij.thread.communicate;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 通过栅栏通信
 *
 * @author: Songdan
 * @create: 2020-01-04 18:23
 **/
public class CyclicBarrierCommunicater {

    public void communicate() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> {
            System.out.println("begin working!!!");
        });
        new Thread(()->{
            while (true) {
                for (int i = 1; i <= 10; i++) {
                    System.out.println("current num is " + i);
                    if (i == 5) {
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        } catch (BrokenBarrierException e) {
                            System.out.println("barrier broken while waiting!!!");
                        }
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {

                }
            }
        }).start();
    }

    public static void main(String[] args) {
        new CyclicBarrierCommunicater().communicate();

    }

}
