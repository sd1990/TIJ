package org.songdan.tij.algorithm.sort;

/**
 * 优先队列
 * @author: Songdan
 * @create: 2019-11-24 17:42
 **/
public class MinPQ<T extends Comparable<T>> {

    private int n;

    private T[] arr;



    public MinPQ(int n) {
        this.n = n;
        arr = (T[]) new Comparable[this.n+1];
    }

    /**
     * 将offset为k的元素上浮
     * @param k
     */
    private void swim(int k) {
        while (k > 1 && less(arr[k], arr[k/2])) {
            exch(k, k/2);
            k = k / 2;
        }
    }

    /**
     * 将offset为k的元素下沉
     * @param k
     */
    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(arr[j+1],arr[j])) {
                j++;
            }
            if (!great(arr[k],arr[j])) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }

    private void exch(int i,int j) {
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private boolean less(T e1,T e2) {
        return e1.compareTo(e2) < 0;
    }

    private boolean great(T e1,T e2) {
        return e1.compareTo(e2) > 0;
    }
}
