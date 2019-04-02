package org.songdan.tij.cache;

/**
 * @author: Songdan
 * @create: 2019-03-29 16:18
 **/
public interface Cache<K,V> {

    V set(K storeKey, V value);

    V get(K storeKey);

    boolean compareAndSet(K storeKey, V oldValue, V newValue);

    V del(K storeKey);

    V putIfAbsent(K key, V value);
}
