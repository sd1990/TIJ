package org.songdan.tij.thread;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class ThrottleDelayQueueTest {

    @Test
    public void offer() {
        ThrottleDelayQueue<DelayedTask> throttleDelayQueue = new ThrottleDelayQueue<>();
        DelayedTask e1 = new DelayedTask(1, 1, 40);
        throttleDelayQueue.offer(e1);
        DelayedTask e2 = new DelayedTask(1, 2, 50);
        throttleDelayQueue.offer(e2);
        DelayedTask e3 = new DelayedTask(1, 3, 60);
        throttleDelayQueue.offer(e3);
        Assert.assertEquals(e3, throttleDelayQueue.peek());
    }

    @Test
    public void submitConcurrent() throws InterruptedException {
        ThrottleDelayQueue<DelayedTask> throttleDelayQueue = new ThrottleDelayQueue<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            executorService.submit(() -> {
                int delay = 50;
                throttleDelayQueue.offer(new DelayedTask(1, finalI, delay));
            });
        }
        Thread.sleep(50);
        System.out.println(throttleDelayQueue.size());
    }


    class DelayedTask implements ThrottleTask {

        private long executeTime;

        private int id;

        private int version;


        public DelayedTask(int id, int version, long delayMills) {
            this.id = id;
            this.version = version;
            this.executeTime = System.currentTimeMillis() + delayMills;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(executeTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS));
        }

        @Override
        public String toString() {
            return "DelayedTask["+super.toString()+"]{" +
                    "executeTime=" + executeTime +
                    ", id=" + id +
                    ", version=" + version +
                    '}';
        }

        @Override
        public String identify() {
            return String.valueOf(id);
        }
    }

}