package org.songdan.tij.designpattern.filters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by SongDan on 2017/6/18.
 */
public class FilterChain {

    private int i = 0;

    private List<MyHttpFilter> filters = new ArrayList<>();

    private Iterator<MyHttpFilter> iterator = filters.iterator();

    public void addFilter(MyHttpFilter filter) {
        filters.add(filter);
    }

    public void doFilter(Worker worker) {
        /*while (iterator.hasNext()) {
            MyHttpFilter filter = iterator.next();
            filter.doFilter(worker, this);
        }
        worker.work();*/
        i++;
        if (i <= filters.size()) {
            MyHttpFilter myFilter = filters.get(i-1);
            myFilter.doFilter(worker, this);
        }
        else {
            worker.work();
        }
    }

}
