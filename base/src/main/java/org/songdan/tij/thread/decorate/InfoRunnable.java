package org.songdan.tij.thread.decorate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: Songdan
 * @create: 2019-01-15 10:25
 **/
public abstract class InfoRunnable implements Runnable,Descriptive {

    private Runnable runnable;

    public InfoRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        System.out.println(descript()+": before execute");
        try {
            runnable.run();
        }finally {
            System.out.println(descript()+": after execute");
        }
    }
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new InfoRunnable(() -> {
            System.out.println("origin task execute!!!");
        }) {
            @Override
            public String descript() {
                return "first task";
            }
        });
        executorService.submit(new InfoRunnable(() -> {
            System.out.println("origin task execute!!!");
        }) {
            @Override
            public String descript() {
                return "second task";
            }
        });
        executorService.submit(new InfoRunnable(() -> System.out.println("origin task execute!!!")) {
            @Override
            public String descript() {
                return "third task";
            }
        });
    }

}
