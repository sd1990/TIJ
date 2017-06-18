package org.songdan.tij.designpattern.filters;

/**
 * 过滤器接口,过滤过程模仿Dubbo组件
 * Created by SongDan on 2017/6/18.
 */
public interface MyDubboFilter {

    public void doFilter(Worker worker);

}
