package org.songdan.tij.throttle;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 节流结果
 *
 * @author: Songdan
 * @create: 2019-03-29 17:26
 **/
public class ThrottleFuture<V> extends FutureTask<V> {

    public ThrottleFuture(Callable<V> callable) {
        super(callable);
    }

    @Override
    public void set(V o) {
        super.set(o);
    }

    @Override
    public void setException(Throwable t) {
        super.setException(t);
    }
}
