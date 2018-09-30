package org.songdan.tij.algorithm;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 滑动窗口限流
 *
 * @author: Songdan
 * @create: 2018-09-30 11:15
 **/
public class MovingCounter {

    private AtomicLong counter = new AtomicLong(0);

    private LinkedList<Long> windows = new LinkedList<>();

    private int windowLength = 100;

    private int limit;


    public MovingCounter(int limit) {
        new Thread(() -> move()).start();
        this.limit = limit;
    }

    public boolean acquire() {
        if (getDiscount()>=limit) {
            return false;
        }
        counter.incrementAndGet();
        return true;
    }

    public long getDiscount() {
        return windows.getLast() - windows.getFirst();
    }

    private void move() {
        while (true) {
            /*
            1. 追加新的窗口
            2. 如果窗口大于阈值，移除最老的窗口
            3. sleep
             */
            windows.addLast(counter.get());
            if (windows.size()>windowLength) {
                windows.removeFirst();
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
