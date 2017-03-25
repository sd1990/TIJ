package org.songdan.tij.thread.evenchecker;

/**
 * Int类型的生成器，作为共享资源
 * Created by PC on 2016/5/14.
 */
public abstract class IntGenerator {

    private volatile boolean canceled = false;

    /**
     * 生成int<br>
     * 核心方法
     *
     * @return
     */
    public abstract int next();

    public boolean isCanceled() {
        return canceled;
    }

    /**
     * 终止资源
     */
    public void cancel() {
        canceled = true;
    }

}
