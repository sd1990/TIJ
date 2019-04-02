package org.songdan.tij.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Songdan
 * @create: 2019-03-30 15:33
 **/
public class LocalCache<K, V> implements Cache<K, V> {

    private ConcurrentHashMap<K, V> storeMap = new ConcurrentHashMap<>();

    @Override
    public V set(K storeKey, V value) {
        return storeMap.put(storeKey, value);
    }

    @Override
    public V get(K storeKey) {
        return storeMap.get(storeKey);
    }

    @Override
    public boolean compareAndSet(K storeKey, V oldValue, V newValue) {
        if (oldValue == null) {
            return storeMap.putIfAbsent(storeKey, newValue) == null;
        }
        return storeMap.replace(storeKey, oldValue, newValue);
    }

    @Override
    public V del(K storeKey) {
        return storeMap.remove(storeKey);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return storeMap.putIfAbsent(key, value);
    }
}
