package org.songdan.tij.thread.evenchecker;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过Lock来同步生成偶数
 * Created by PC on 2016/5/14.
 */
public class MutexEventGenerator extends IntGenerator {

    /**
     * 使用可重入的Lock锁
     */
    private Lock lock = new ReentrantLock();

    private int val = 0;

    @Override
    public int next() {
        lock.lock();
        try {
            val++;
            val++;
            return val;
        }
        finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        EvenChecker.test(new MutexEventGenerator());
    }
}
