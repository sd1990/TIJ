package org.songdan.tij.array.collection;

/**
 * 相等性的比较器
 * Created by PC on 2016/5/3.
 */
public interface Equator<T> {

    public boolean equate(T o1, T o2);

    int hash();

}
