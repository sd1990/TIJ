package org.songdan.tij.thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Songdan
 * @date 2016/6/3
 */
public class ExecutorsUtil {

    public static void execute(ExecutorService executor, int concurrent, Runnable action) {
        for (int i = 0; i < concurrent; i++) {
            executor.execute(action);
        }
        try {
            TimeUnit.SECONDS.sleep(10);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdownNow();
    }

}
