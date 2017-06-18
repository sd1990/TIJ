package org.songdan.tij.designpattern.filters;

/**
 * Created by SongDan on 2017/6/18.
 */
public class HttpLoggerFilter implements MyHttpFilter {

    @Override
    public void doFilter(Worker worker, FilterChain filterChain) {
        System.out.println("LoggerFilter start ...");
        filterChain.doFilter(worker);
        System.out.println("LoggerFilter end ...");
    }
}
