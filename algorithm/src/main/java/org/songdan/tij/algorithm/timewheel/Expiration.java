package org.songdan.tij.algorithm.timewheel;

/**
 * 过期动作
 */
public interface Expiration extends Comparable<Expiration> {

    void expired();

    long getExpireTime();

}
