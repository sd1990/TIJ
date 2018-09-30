package org.songdan.tij.algorithm;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 滑动窗口限流,精细版
 *
 * @author: Songdan
 * @create: 2018-09-30 11:15
 **/
public class MovingCounterV2 {

    private AtomicLong counter = new AtomicLong(0);

    private LinkedList<Limiter> windows = new LinkedList<>();

    private int windowLength = 50;

    private volatile long limit;

    private volatile boolean init;


    public MovingCounterV2(int limit) {
        this.limit = limit;
        new Thread(() -> move()).start();
        while (!init) {
        }
    }

    public boolean acquire() {
        synchronized (windows) {
            return windows.getLast().acquire();
        }
    }

    private class Limiter {
        private AtomicLong atomicLong = new AtomicLong(0);
        private Long limit;
        private Long bornTime = System.currentTimeMillis();
        private Long index = counter.getAndIncrement();

        public Limiter(Long limit) {
            this.limit = limit;
        }

        public long getCount() {
            return atomicLong.get();
        }

        public boolean acquire() {
            if (getCount() >= limit) {
                return false;
            }
            atomicLong.incrementAndGet();
            return true;
        }

        @Override
        public String toString() {
            return "Limiter{" +
                    "atomicLong=" + atomicLong +
                    ", limit=" + limit +
                    ", bornTime=" + bornTime +
                    ", index=" + index +
                    '}';
        }
    }

    private void move() {
        long pre = 0;
        while (true) {
            /*
            1. 追加新的窗口
            2. 如果窗口大于阈值，移除最老的窗口
            3. sleep
             */
            long removeCount = 0;
            synchronized (windows) {
                if (windows.size() >= windowLength) {
                    Limiter firstWindow = windows.removeFirst();
                    removeCount = firstWindow.getCount();
                    //help gc
                    firstWindow = null;
                }
                pre -= removeCount;
                if (windows.size() > 0) {
                    Limiter lastWindow = windows.getLast();
                    Long lastWindowCount = lastWindow.getCount();
                    pre += lastWindowCount;
                    //并发问题,获取完上一个窗口的count后，在新添加窗口之前，有流量进入
                } else {
                }
                Limiter limiter = new Limiter(this.limit - pre);
                System.out.println(limiter);
                windows.addLast(limiter);
            }
            if (!init) {
                init = true;
            }

            if (pre < 0) {
                throw new RuntimeException("pre error:" + pre);
            }
            if (pre > limit) {
                throw new RuntimeException("pre overflow:" + pre);
            }
            try {
                Thread.sleep(1000 / windowLength);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
