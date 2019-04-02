package org.songdan.tij.throttle;

import org.songdan.tij.cache.LocalCache;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * JVM级别节流
 * @author: Songdan
 * @create: 2019-03-30 15:17
 **/
public class ThrottleExecutor<V> {

    private static ScheduledThreadPoolExecutor    scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors());

    private LocalCache<String, Long> throttleCache               = new LocalCache<>();

    private LocalCache<String, ThrottleFuture<V>> futureCache                 = new LocalCache<>();

    private Lock                                  lock                        = new ReentrantLock();

    public Future<V> submit(ThrottleTask<V> throttleTask) {
        lock.lock();
        try {
            ThrottleFuture<V> future = futureCache.get(throttleTask.identify());
            if (future == null) {
                ThrottleFuture<V> throttleFuture = new ThrottleFuture<>(throttleTask);
                ThrottleFuture<V> preFuture = futureCache.putIfAbsent(throttleTask.identify(), throttleFuture);
                if (preFuture == null) {
                    future = throttleFuture;
                } else {
                    future = preFuture;
                }
            }
            Long time = getPrePeriod(throttleTask.identify());
            long now = System.currentTimeMillis();
            Long newValue = now +throttleTask.period();
            if (store(throttleTask.identify(), time, newValue)) {
                ThrottleFuture<V> finalFuture = future;
                scheduledThreadPoolExecutor.schedule(() -> {
                    lock.lock();
                    boolean needInvoke = false;
                    try {
                        long wakeTime = System.currentTimeMillis();
                        Long prePeriod = getPrePeriod(throttleTask.identify());
                        long storeTime = Optional.ofNullable(prePeriod).orElse(now);
                        if (wakeTime >= storeTime) {
                            needInvoke = true;
                            //说明窗口期没有新事件过来
                            remove(throttleTask.identify());
                        } //说明触发本次调用的事件已经无效
                    } finally {
                        lock.unlock();
                    }
                    if (needInvoke) {
                        V value;
                        try {
                            //将这个结果设置到future中
                            value = throttleTask.call();
                            finalFuture.set(value);
                        } catch (Exception e) {
                            finalFuture.setException(e);
                        }
                    }
                }, throttleTask.period(), TimeUnit.MILLISECONDS);
            }
            return future;
        } finally {
            lock.unlock();
        }
    }

    protected Long getPrePeriod(String identify) {
        return throttleCache.get(identify);
    }

    protected boolean store(String identify, Long oldPeriod, Long newPeriod) {
        return throttleCache.compareAndSet(identify, oldPeriod, newPeriod);
    }

    protected long remove(String identify) {
        futureCache.del(identify);
        return throttleCache.del(identify);

    }

    public void shutdown() {
        scheduledThreadPoolExecutor.shutdown();
        try {
            scheduledThreadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (!scheduledThreadPoolExecutor.isShutdown()) {
            List<Runnable> taskList = scheduledThreadPoolExecutor.shutdownNow();
            if (taskList.size() > 0) {
                System.out.println("unfinished task size is " + taskList.size());
            }
        }
    }
}
