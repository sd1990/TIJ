package org.songdan.tij.throttle;

/**
 * @author: Songdan
 * @create: 2019-03-29 12:12
 **/
public abstract class AbstractThrottable implements Throttlable {

    private long period;

    public AbstractThrottable(long period) {
        this.period = period;
    }

    @Override
    public long period() {
        return period;
    }
}
