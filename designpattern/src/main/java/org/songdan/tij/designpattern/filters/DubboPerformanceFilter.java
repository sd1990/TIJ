package org.songdan.tij.designpattern.filters;

/**
 * Created by SongDan on 2017/6/18.
 */
public class DubboPerformanceFilter implements MyDubboFilter {

    @Override
    public void doFilter(Worker worker) {
        long start = System.currentTimeMillis();
        worker.work();
        System.out.println("worker cost time :" + (System.currentTimeMillis() - start));
    }
}
