package org.songdan.tij.thread.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 测试线程数量退出的情况
 * @author: Songdan
 * @create: 2021-01-07 12:27
 **/
public class ExecutorsExit {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 5, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        for (int i = 0; i < 2; i++) {
            threadPoolExecutor.submit(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        TimeUnit.SECONDS.sleep(5);
        System.out.println(threadPoolExecutor);
        threadPoolExecutor.shutdownNow();
    }

}
