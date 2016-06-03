package org.songdan.tij.thread;

import org.songdan.tij.random.RandomUtil;

import java.util.concurrent.*;

/**
 * Created by PC on 2016/5/17.
 */
public class ProductConsumerDemo3 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        BlockingQueue<String> queue = new LinkedBlockingQueue<>(5);
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) {
                executorService.submit(new Consum(queue));
            }
            else {
                executorService.submit(new Product(queue));
            }
        }
        try {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
}

class Consum implements Runnable {
    private BlockingQueue<String> queue;

    public Consum(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
//        while (!Thread.currentThread().isInterrupted()) {
            String t = null;
            try {
                while ((t = queue.take()) != null) {
                    System.out.println("consumer" + t);
                    TimeUnit.MILLISECONDS.sleep(200);
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
//        }
    }
}

class Product implements Runnable {

    private BlockingQueue<String> queue;

    public Product(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            String t = RandomUtil.getRandom16String();
            try {
                System.out.println("product " + t);
                queue.put(t);
                TimeUnit.MILLISECONDS.sleep(500);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
