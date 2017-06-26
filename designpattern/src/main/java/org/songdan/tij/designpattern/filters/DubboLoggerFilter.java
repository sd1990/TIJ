package org.songdan.tij.designpattern.filters;

/**
 * Created by SongDan on 2017/6/18.
 */
public class DubboLoggerFilter implements MyDubboFilter {

    @Override
    public void doFilter(Worker worker) {
        System.out.println("logger log work ...");
        worker.work();
    }
}
