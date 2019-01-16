package org.songdan.tij.thread.decorate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Songdan
 * @create: 2019-01-15 10:28
 **/
public abstract class TimeCountRunnable implements Runnable,Descriptive {

    private Runnable runnable;

    public TimeCountRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            runnable.run();
        } finally {
            System.out.println(descript()+" cost mills: " + (System.currentTimeMillis() - start));
        }

    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new TimeCountRunnable(() -> {
            System.out.println("origin task execute!!!");
        }) {
            @Override
            public String descript() {
                return "first task";
            }
        });
        executorService.submit(new TimeCountRunnable(() -> {
            System.out.println("origin task execute!!!");
        }) {
            @Override
            public String descript() {
                return "second task";
            }
        });
        executorService.submit(new TimeCountRunnable(() -> System.out.println("origin task execute!!!")) {
            @Override
            public String descript() {
                return "third task";
            }
        });
    }

}
