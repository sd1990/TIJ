package org.songdan.tij.thread.computeCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 使用ConcureentHashMap实现缓存，提高并发性，但是多个线程同时初始化相同的值时，会都计算，资源浪费
 * Created by PC on 2016/8/21.
 */
public class Memorizer2<A,V> implements Computable<A,V> {

    private Computable<A,V> target;

    private ConcurrentHashMap<A, V> map = new ConcurrentHashMap<>();

    public Memorizer2(Computable<A,V> target) {
        this.target = target;
    }

    @Override
    public V compute(A a) {
        V v = map.get(a);
        if (v == null) {
            v = target.compute(a);
            map.putIfAbsent(a, v);
        }
        return v;
    }
}
