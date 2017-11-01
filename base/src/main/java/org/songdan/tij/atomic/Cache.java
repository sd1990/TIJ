package org.songdan.tij.atomic;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 带有定时更新功能的缓存，用于只有一个结果，而非常见的k-v格式的，kv格式使用guava cache
 *
 * @author song dan
 * @since 01 十一月 2017
 */
public class Cache<T> {

    private AtomicReference<Future<T>> cacheReference = new AtomicReference<>();

    private Callable<T> call;

    private int num;

    private ChronoUnit timeUnit;

    private volatile LocalDateTime expiredTime;

    public Cache(Callable<T> call, int num, ChronoUnit timeUnit) {
        this.call = call;
        this.num = num;
        this.timeUnit = timeUnit;
    }

    public static void main(String[] args) throws InterruptedException {
        Cache<Long> cache = new Cache<Long>(() -> {
            TimeUnit.SECONDS.sleep(2);
            return 0l;
        }, 3, ChronoUnit.SECONDS);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 20; i++) {
            System.out.println("提交" + i);
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + ">>>>" + cache.get());
            });
        }
        System.out.println("提交完成");
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("over");
    }

    public T get() {
        while (true) {
            try {
                if (expiredTime != null && expiredTime.compareTo(LocalDateTime.now()) < 0) {
                    cacheReference.set(null);
                }
                Future<T> future = cacheReference.get();
                if (future == null) {
                    FutureTask<T> ft = new FutureTask<>(call);
                    if (cacheReference.compareAndSet(null, ft)) {
                        // 计算
                        ft.run();
                        // 计算下一次失效时间
                        expiredTime = LocalDateTime.now().plus(num, timeUnit);
                    }
                }
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