package org.songdan.tij.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 阈值清零策略
 *
 * @author: Songdan
 * @create: 2018-09-29 11:35
 **/
public class ThresholdClearStrategy implements ClearStrategy {

    private Long threshold;

    public ThresholdClearStrategy(Long threshold) {
        this.threshold = threshold;
    }

    @Override
    public boolean clear(AtomicLong counter) {
        if (needClear(counter)) {
            return counter.compareAndSet(counter.get(), 0);
        }
        return false;
    }

    @Override
    public boolean needClear(AtomicLong counter) {
        return counter.get() >= threshold;
    }
}
