package org.songdan.tij.thread.cooperation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发组件，基于Condition的有界缓存
 *
 * Created by Songdan on 2016/9/11.
 */
public class ConditionBoundBuffer<T> {

    private Lock lock = new ReentrantLock();

    private Condition notEmpty = lock.newCondition();

    private Condition notFull = lock.newCondition();

    private T[] items = (T[]) new Object[10];

    private int tail,head,count;

    /**
     * 获取缓存头部元素，并移除，如果缓存为空，一直阻塞知道缓存不为空
     * @return
     * @throws InterruptedException
     */
    public T take() throws InterruptedException {
        lock.lock();
        try {
            while (count==0) {
                notEmpty.await();
            }
            T item = items[head];
            items[head] = null;
            if (head++==items.length) {
                head=0;
            }
            count--;
            notFull.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 向缓存中放入元素，如果缓存满了，一直阻塞
     * @param item
     * @throws InterruptedException
     */
    public void put(T item) throws InterruptedException {
        lock.lock();
        try {
            while (count==items.length) {
                notFull.await();
            }
            items[tail] = item;
            if(tail++==items.length) {
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
}
