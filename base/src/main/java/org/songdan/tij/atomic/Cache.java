package org.songdan.tij.atomic;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 带有定时更新功能的缓存，用于只有一个结果，而非常见的k-v格式的，kv格式使用guava cache
 *
 * @author song dan
 * @since 01 十一月 2017
 */
public class Cache<T> {

    final AtomicReference<Future<T>> cacheReference = new AtomicReference<>();

    private Callable<T> call;

    public Cache(Callable<T> call, int minute, TimeUnit timeUnit) {
        this.call = call;
        Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
            cacheReference.set(null);
        }, 1, minute, timeUnit);

    }

    public T get() {
        while (true) {
            Future<T> future = cacheReference.get();
            if (future == null) {
                FutureTask<T> ft = new FutureTask<>(call);
                if (cacheReference.compareAndSet(null, ft)) {
                    ft.run();
                }
            }
            try {
                return cacheReference.get().get();
            } catch (NullPointerException e) {
                // 只有定时任务清除的时候有可能为空或者获取到防止缓存污染的补偿 继续循环即可

            } catch (Throwable e) {
                cacheReference.set(null);
                throw new RuntimeException(e);
            }
        }
    }

}