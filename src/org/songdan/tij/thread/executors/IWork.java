package org.songdan.tij.thread.executors;

/**
 * 工作接口，不同实现类有不同的工作方式，串行或并行
 *
 * @author Songdan
 * @date 2016/8/27
 */
public interface IWork {

    /**
     * 工作方法
     */
    public void work();

}
