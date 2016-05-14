package org.songdan.tij.array.thread.evenchecker;

/**
 * 通过synchronize关键字同步next方法
 * Created by PC on 2016/5/14.
 */
public class SynchronizeEvenGenerator extends IntGenerator {

    private int val = 0;

    @Override
    public synchronized int next() {
        val++;
        val++;
        return val;
    }

    public static void main(String[] args) throws InterruptedException {
        EvenChecker.test(new SynchronizeEvenGenerator());
    }
}
