package org.songdan.tij.thread.computeCache;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap实现的缓存，使用synchronized的关键字保证线程同步，降低了并发性，效率太低
 * Created by PC on 2016/8/21.
 */
public class Memorizer1<A,V> implements Computable<A,V> {

    private Map<A, V> map = new HashMap<>();

    private Computable<A,V> target;

    public Memorizer1(Computable<A,V> target) {
        this.target = target;
    }

    @Override
    public synchronized V compute(A a) {
        V v = map.get(a);
        if (v!=null) {
            v = target.compute(a);
            map.put(a, v);
        }
        return v;
    }
}
