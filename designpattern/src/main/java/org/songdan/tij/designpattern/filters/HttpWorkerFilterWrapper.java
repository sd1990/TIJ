package org.songdan.tij.designpattern.filters;

/**
 * Created by SongDan on 2017/6/18.
 */
public class HttpWorkerFilterWrapper implements Worker {

    private Worker worker;

    public HttpWorkerFilterWrapper(Worker worker) {
        this.worker = worker;
    }

    @Override
    public void work() {
        buildFilterChain().doFilter(worker);
    }

    private FilterChain buildFilterChain() {
        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HttpLoggerFilter());
        filterChain.addFilter(new HttpPerformanceFilter());
        return filterChain;
    }
}
