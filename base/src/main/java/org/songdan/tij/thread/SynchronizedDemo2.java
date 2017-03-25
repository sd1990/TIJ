package org.songdan.tij.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步代码块示例
 *
 * @author SONGDAN
 */
public class SynchronizedDemo2 {

    public static void main(String[] args) {
        Task task = new Task();
        IntGenerator demo = new MyIntGenerator();
        task.setDemo(demo);
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);
        Thread t5 = new Thread(task);
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}

class MyIntGenerator extends IntGenerator {

    private int i = 0;

    @Override
    public int next() {
        Object locker = LockSupplier.getLocker(new String("12312345678901234567890"));
        synchronized (locker) {
            i++;
            try {
                Thread.sleep(50);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
            return i;
        }
    }

}

class LockSupplier {

    /**
     * 自定义锁库,要求key必须复写了hashcode
     */
    private static final ConcurrentHashMap<String, Object> LOCK_MAP = new ConcurrentHashMap<String, Object>();

    private static LockSupplier supplier = new LockSupplier();

    private ReentrantLock locker = new ReentrantLock();

    /**
     * 获取key对应的locker
     *
     * @param key
     * @return
     */
    private Object acquire(String key) {
        locker.lock();
        try {
            try {
                Thread.sleep(10);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object locker = LOCK_MAP.get(key);

            if (locker == null) {
                locker = new String(key);
                LOCK_MAP.put(key, locker);
            }
            return locker;
        }
        finally {
            locker.unlock();
        }
    }

    /**
     * 获取锁
     *
     * @param key
     * @return
     */
    public static Object getLocker(String key) {
        return supplier.acquire(key);
    }

    /**
     * 私有化构造，保证全局只有一个对象
     */
    private LockSupplier() {
    }

}

