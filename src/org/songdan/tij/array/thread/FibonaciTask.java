package org.songdan.tij.array.thread;

import org.songdan.tij.array.generics.Fibonacci;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Songdan
 * @date 2016/5/11
 */
public class FibonaciTask implements Runnable {

    private static AtomicInteger count = new AtomicInteger(0);

    private int id = count.incrementAndGet();

    private int n = 5;

    private Fibonacci fibonacci = new Fibonacci();

    public FibonaciTask() {
    }

    public FibonaciTask(int n) {
        this.n = n;
    }

    @Override
    public void run() {
        while (n-- != 0) {
            System.out.println(id+":"+fibonacci.next());
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= 5; i++) {
            new Thread(new FibonaciTask(i)).start();
        }
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 5; i++) {
            executorService.execute(new FibonaciTask(i));
        }
    }
}
