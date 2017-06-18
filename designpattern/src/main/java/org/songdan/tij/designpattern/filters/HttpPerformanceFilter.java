package org.songdan.tij.designpattern.filters;

/**
 * Created by SongDan on 2017/6/18.
 */
public class HttpPerformanceFilter implements MyHttpFilter {

    @Override
    public void doFilter(Worker worker, FilterChain filterChain) {
        long start = System.currentTimeMillis();
        filterChain.doFilter(worker);
        long end = System.currentTimeMillis();
        System.out.println("worker cost time :"+(end-start));
    }
}
