package org.songdan.tij.thread.productAndConsumer;


import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: Songdan
 * @create: 2019-08-16 10:32
 **/
class ConsumeProgressRate {
    private TreeSet<Long> poiTreeSet = new TreeSet<>();

    private Lock lock = new ReentrantLock();

    private AtomicLong consumeCount = new AtomicLong(0);
    private AtomicLong poiCount = new AtomicLong(0);

    private AtomicLong offset = new AtomicLong(0);

    private volatile long maxPoiId = 0l;

    /**
     * 必须保证顺序追加
     *
     * @param poiIdList
     * @return
     */
    public boolean append(List<Long> poiIdList) {
        lock.lock();
        try {
            long temp = maxPoiId;
            int validCount = 0;
            for (Long poiId : poiIdList) {
                if (poiTreeSet.add(poiId)) {
                    validCount++;
                    maxPoiId = poiId;
                }
            }
            poiCount.addAndGet(validCount);
            return maxPoiId > temp;
        } finally {
            lock.unlock();
        }
    }

    public Long remove(List<Long> list) {
        lock.lock();
        try {
            long result = -1;
            if (!poiTreeSet.isEmpty()) {
                int removeCount = 0;
                //这个地方不能使用treeset.last(),因为如果treeset被清空的话，last()并不能反映真实最大的ID
                result = maxPoiId;
                for (Long poiId : list) {
                    if (poiTreeSet.remove(poiId)) {
                        removeCount++;
                    }
                }
                consumeCount.addAndGet(removeCount);
                if (!poiTreeSet.isEmpty()) {
                    result = poiTreeSet.first();
                }
            }
            return result;
        } finally {
            lock.unlock();
        }
    }

    public void storeOffSet(Long newOffset) {
        long old = offset.get();
        while (newOffset > old) {
            if (offset.compareAndSet(old, newOffset)) {
                //TODO store redis
//                System.out.println("consume offset : " + newOffset);
                return;
            }
            old = offset.get();
        }
    }

    public void showRate() {
        System.out.println("addPoiCount:" + poiCount.get() + " consumePoiCount:" + consumeCount.get() + ",consumeOffSet:" + offset.get() + ",maxPoiId:" + maxPoiId);
    }
}
