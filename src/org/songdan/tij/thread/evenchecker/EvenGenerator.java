package org.songdan.tij.thread.evenchecker;

/**
 * Created by PC on 2016/5/14.
 */
public class EvenGenerator extends IntGenerator {

    private int val = 0;

    @Override
    public int next() {
        val++;
        //在这一步会发生竞争共享资源
        val++;
        return val;
    }

    public static void main(String[] args) throws InterruptedException {
        IntGenerator intGenerator = new EvenGenerator();
        EvenChecker.test(intGenerator);
    }
}
