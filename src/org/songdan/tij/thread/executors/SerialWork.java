package org.songdan.tij.thread.executors;

/**
 * 串行工作
 *
 * @author Songdan
 * @date 2016/8/27
 */
public class SerialWork implements IWork {

    @Override
    public void work() {
        long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < 5; i++) {
                new Worker().work();
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
