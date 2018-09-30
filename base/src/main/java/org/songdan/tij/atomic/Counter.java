package org.songdan.tij.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 计数器，自动清零功能
 *
 * @author: Songdan
 * @create: 2018-09-29 11:01
 **/
public class Counter {

    private AtomicLong counter;

    private ClearStrategy clearStrategy;

    public Counter(ClearStrategy clearStrategy) {
        this.counter = new AtomicLong(0);
        this.clearStrategy = clearStrategy;
    }

    public Long increase() {
        if (clearStrategy != null) {
            while (true) {
                if (clearStrategy.needClear(this.counter)) {
                    clearStrategy.clear(this.counter);
                }
                if (!clearStrategy.needClear(this.counter)) {
                    long cur = this.counter.get();
                    if (counter.compareAndSet(cur, cur + 1)) {
                        return cur + 1;
                    }
                }
            }
        } else {
            return this.counter.incrementAndGet();
        }
    }

}
