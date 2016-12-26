package org.songdan.tij.thread.atomic;

import org.songdan.tij.thread.executors.ExecutorsUtil;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicInteger示例类
 *
 * @author Songdan
 * @date 2016/6/3
 */
public class AtomicIntegerDemo {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private int count = 20;

    public void increate() throws InterruptedException {
        if (atomicInteger.get() <= 20) {
            TimeUnit.MILLISECONDS.sleep(200);
            int x = atomicInteger.incrementAndGet();
            System.out.println(x);
            if (x > count) {
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo demo = new AtomicIntegerDemo();
        ExecutorsUtil.execute(Executors.newCachedThreadPool(), 4, new AtomicIntegerTest(demo));
    }
}

class AtomicIntegerTest implements Runnable {

    private AtomicIntegerDemo demo;

    public AtomicIntegerTest(AtomicIntegerDemo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while (true) {
            try {
                demo.increate();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
