package org.songdan.tij.thread.executors;

import java.util.concurrent.TimeUnit;

/**
 * 工作任务实例
 *
 * @author Songdan
 * @date 2016/8/27
 */
public class Worker {

    public void work() throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
    }

    public static void main(String[] args) {
        IWork work = new SerialWork();
        work.work();
        work = new ParallelWork();
        work.work();
    }

}
