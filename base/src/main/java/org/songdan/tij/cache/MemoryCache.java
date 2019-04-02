package org.songdan.tij.cache;

import org.songdan.tij.enums.Pair;
import org.songdan.tij.exception.ExceptionUtil;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @author: Songdan
 * @create: 2018-11-07 13:11
 **/
public class MemoryCache<K, V> implements Iterable<Pair<K, V>> {

    private ConcurrentHashMap<K, Future<V>> cache = new ConcurrentHashMap<>();

    private Function<K, V> function;

    private MemoryCache() {
    }

    public static Builder<Object, Object> builder() {
        return new Builder<>();
    }

    public static <K, V> MemoryCache<K, V> build(Function<K, V> loader) {
        MemoryCache<K, V> memoryCache = new MemoryCache<>();
        memoryCache.function = loader;
        return memoryCache;
    }

    public V get(final K key) throws InterruptedException {
        while (true) {
            Future<V> future = cache.get(key);
            if (future == null) {
                FutureTask<V> futureTask = new FutureTask<>(new Callable<V>() {
                    @Override
                    public V call() throws Exception {
                        return function.apply(key);
                    }
                });
                future = cache.putIfAbsent(key, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }
            try {
                return future.get();
            } catch (CancellationException e) {
                cache.remove(key);
            } catch (ExecutionException e) {
                throw ExceptionUtil.launderThrowable(e);
            }
        }
    }

    public Future<V> remove(K key) {
        return cache.remove(key);
    }


    @Override
    public Iterator<Pair<K, V>> iterator() {
        return new Iterator<Pair<K, V>>() {
            @Override
            public boolean hasNext() {
                return cache.entrySet().iterator().hasNext();
            }

            @Override
            public Pair<K, V> next() {
                return wrap(cache.entrySet().iterator().next());
            }

            private Pair<K, V> wrap(Map.Entry<K, Future<V>> next) {
                try {
                    return Pair.of(next.getKey(), next.getValue().get());
                } catch (InterruptedException | ExecutionException e) {
                    throw ExceptionUtil.launderThrowable(e);
                }
            }

            @Override
            public void remove() {
                cache.entrySet().iterator().remove();
            }
        };
    }

    private static class NullFunction implements Function {

        static NullFunction INSTANCE = new NullFunction();

        @Override
        public Object apply(Object input) {
            return null;
        }
    }

    public static class Builder<K, V> {

        public Function<? super K, ? extends V> cacheLoader;

        public Builder<K, V> cacheLoader(Function<? super K, ? extends V> cacheLoader) {
            this.cacheLoader = cacheLoader;
            return (Builder<K, V>) this;
        }

        public <K1 extends K, V1 extends V> MemoryCache<K1, V1> build(Function<? super K1, ? extends V1> cacheLoader) {
            MemoryCache<K1, V1> cache = new MemoryCache<>();
            cache.function = (Function<K1, V1>) Optional.ofNullable(cacheLoader).orElse(NullFunction.INSTANCE);
            return cache;
        }
    }

}
