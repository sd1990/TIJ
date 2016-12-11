package org.songdan.tij.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Songdan
 * @date 2016/8/18
 */
public class NumberRange {

    //不变性条件：lower<=upper
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(10);

    public void setLower(int i) {
        if (i > upper.get()) {
            throw new IllegalArgumentException("参数不能大于上边界");
        }
        lower.set(i);
    }

    public void setUpper(int i) {
        if (i < lower.get()) {
            throw new IllegalArgumentException("参数不能小于下边界");
        }
        upper.set(i);
    }

    public boolean isOK(int i) {
        return i <= upper.get() && i >= lower.get();
    }

    public boolean isOK() {
        return upper.get() >= lower.get();
    }

    public static void main(String[] args) throws InterruptedException {
        NumberRange numberRange = new NumberRange();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Random random = new Random();
        /*executorService.submit(() -> {
            while (numberRange.isOK()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                numberRange.setLower(5);
            }
        });
        executorService.submit(() -> {
            while (numberRange.isOK()){
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                numberRange.setUpper(4);
            }
        });*/
        TimeUnit.SECONDS.sleep(10);
        System.out.println(numberRange.lower.get());
        System.out.println(numberRange.upper.get());
    }

}
