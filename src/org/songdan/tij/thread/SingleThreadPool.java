package org.songdan.tij.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 创建一个使用一个单独工作线程来执行无边界队列中任务，它会序列化所有提交给它的任务，按照任务被提交的顺序来执行
 * @author Songdan
 * @date 2016/5/11
 */
public class SingleThreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new LiftOff());
        }
        executorService.shutdown();
    }
}
