package org.songdan.tij.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 周期清零策略
 * @author: Songdan
 * @create: 2018-09-29 11:42
 **/
public class PeriodClearStrategy implements ClearStrategy {

    private int           period;

    private TimeUnit timeUnit;

    private long          periodMill;

    private volatile long start;

    private volatile long end;

    public PeriodClearStrategy(int period, TimeUnit timeUnit) {
        this.period = period;
        this.timeUnit = timeUnit;
        periodMill = timeUnit.toMillis(period);
        start = System.currentTimeMillis();
        end = start + periodMill;
    }

    @Override
    public boolean clear(AtomicLong counter) {
        if (needClear(counter)) {
            long l = counter.get();
            if (counter.compareAndSet(l, 0)) {
                start = System.currentTimeMillis();
                end = start + periodMill;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean needClear(AtomicLong counter) {
        return System.currentTimeMillis() >= end;
    }
}
