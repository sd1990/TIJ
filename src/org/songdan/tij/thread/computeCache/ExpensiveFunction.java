package org.songdan.tij.thread.computeCache;

/**
 * Created by PC on 2016/8/21.
 */
public class ExpensiveFunction implements Computable<String,Integer> {

    @Override
    public Integer compute(String s) {
        return new Integer(s);
    }
}

