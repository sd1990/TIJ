package org.songdan.tij.designpattern.filters;

/**
 * 模仿Http的filter
 * Created by SongDan on 2017/6/18.
 */
public interface MyHttpFilter {

    void doFilter(Worker worker, FilterChain filterChain);

}
