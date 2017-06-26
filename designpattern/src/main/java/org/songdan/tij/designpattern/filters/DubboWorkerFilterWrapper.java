package org.songdan.tij.designpattern.filters;

/** 装饰者模式，对真正工作者的组装过滤连
 * Created by SongDan on 2017/6/18.
 */
public class DubboWorkerFilterWrapper implements Worker {

    private Worker real;

    public DubboWorkerFilterWrapper(Worker real) {
        this.real = real;
    }

    @Override
    public void work() {
        buildFilterChain().work();
    }

    private Worker buildFilterChain() {
        Worker worker = real;
        MyDubboFilter[] filters = { new DubboLoggerFilter(), new DubboPerformanceFilter() };
        for (int i = 0; i < filters.length; i++) {
            final MyDubboFilter filter = filters[i];
            final Worker finalWorker = worker;
            worker = new Worker() {

                @Override
                public void work() {
                    filter.doFilter(finalWorker);
                }
            };
        }
        return worker;
    }
}
