package org.songdan.tij.throttle;

import java.util.concurrent.Callable;

/**
 * 节流任务
 */
public interface ThrottleTask<V> extends Callable<V>,Throttlable {
}
