package org.songdan.tij.thread.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo {

    public static void main(String[] args) {
        System.out.println("hello world");
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ExecutorCompletionService<Integer> completionService = new ExecutorCompletionService<>(newCachedThreadPool);
        for (int i = 0; i < 10; i++) {
            completionService.submit(new MyTask(),i);
            System.out.println("**************         " + i + "  **********************");
        }

        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            } catch (ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ":run ....");
            throw new RuntimeException("XXXX");
        }

    }

}
