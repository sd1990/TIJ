package org.songdan.tij.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 计数器清零策略
 */
public interface ClearStrategy {

    boolean clear(AtomicLong counter);

    boolean needClear(AtomicLong counter);

}
