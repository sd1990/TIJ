package org.songdan.tij.thread.computeCache;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 使用ConcurrentHashMap和FutureTash实现计算缓存
 * Created by PC on 2016/8/21.
 */
public class Memorizer3<A, V> implements Computable<A, V> {

    private Computable<A, V> target;

    private ConcurrentHashMap<A, Future<V>> map = new ConcurrentHashMap<>();

    public Memorizer3(Computable<A, V> target) {
        this.target = target;
    }

    @Override
    public V compute(final A a) {
        Future<V> futureTask = map.get(a);
        //如果没有正在执行计算的任务，就放置
        if (futureTask == null) {
            Callable<V> eval = new Callable<V>() {

                @Override
                public V call() throws Exception {
                    return target.compute(a);
                }
            };
            FutureTask<V> task = new FutureTask<>(eval);
            //确保只放置一个，并只计算一次
            futureTask = map.putIfAbsent(a, futureTask);
            if (futureTask == null) {
                futureTask = task;
                task.run();
            }
        }
        if (futureTask != null) {
            try {
                V v = futureTask.get();
            }
            catch (ExecutionException e) {
                //如果出现异常，说明计算失败，删除
                map.remove(a);
            }
            catch (InterruptedException e) {
                map.remove(a);
            }
        }
        return null;
    }
}
