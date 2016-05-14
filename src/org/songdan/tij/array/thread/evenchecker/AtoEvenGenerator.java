package org.songdan.tij.array.thread.evenchecker;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过原子类实现同步
 * Created by PC on 2016/5/14.
 */
public class AtoEvenGenerator extends IntGenerator {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public int next() {
        return atomicInteger.addAndGet(2);
    }

    public static void main(String[] args) throws InterruptedException {
        EvenChecker.test(new AtoEvenGenerator());
    }
}
