package org.songdan.tij.thread.computeCache;

/**
 * 可用于计算的接口
 * Created by PC on 2016/8/21.
 */
public interface Computable<A, V> {

    public V compute(A a);

}
