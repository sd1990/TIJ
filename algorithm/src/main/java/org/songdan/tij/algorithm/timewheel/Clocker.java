package org.songdan.tij.algorithm.timewheel;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 表盘，负责推动timewheel向前
 *
 * @author: Songdan
 * @create: 2018-10-02 21:23
 **/
public class Clocker {

    public static final int INTERVAL = 10;

    private DelayQueue<Slot> delayQueue = new DelayQueue<>();

    private Slot[] slots = new Slot[INTERVAL];

    private ExpirationStore expirationStore;

    private Slot current;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    private int epoch;

    public Clocker(ExpirationStore expirationStore) {
        this.expirationStore = expirationStore;
        init();
    }

    private void init() {
        loadData();
        //加载数据
        executorService.submit(() -> {
            try {
                execute();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void execute() throws InterruptedException {
        while ((current = delayQueue.take()) != null) {
            System.out.println("current index points : " + current.getPoint());
            Set<Expiration> eventList = current.getEventList();
            Iterator<Expiration> iterator = eventList.iterator();
            while (iterator.hasNext()) {
                Expiration event = iterator.next();
                event.expired();
                iterator.remove();
            }
            if (current.getPoint()+1==INTERVAL) {
                epoch++;
                loadData();
            }
        }
    }

    private void loadData() {
        for (int i = 0; i < INTERVAL; i++) {
            slots[i] = new Slot(i);
            delayQueue.put(slots[i]);
        }
        long leftClose = epoch * INTERVAL;
        long maxOpen = (epoch + 1) * INTERVAL;
        List<Expiration> expirationList = expirationStore.getRange(leftClose,maxOpen);
        System.out.println(expirationList);
        for (Expiration expiration : expirationList) {
            getSlot((int) expiration.getExpireTime()).add(expiration);
        }
    }

    public int getCurrent() {
        return current.getPoint();
    }

    public Slot getSlot(int seconds) {
        return slots[seconds % INTERVAL];
    }

    public static void main(String[] args) {
        Clocker clocker = new Clocker(new ExpirationStore());
    }

}
