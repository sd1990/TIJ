package org.songdan.tij.thread.threadlocal;

import java.util.concurrent.*;

/**
 * @author: Songdan
 * @create: 2019-01-30 16:11
 **/
public class ThreadLocalCase {

    private static ThreadLocal<Integer> holder = new ThreadLocal<>();

    private static InheritableThreadLocal<Integer> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        threadlocal();
        inheritableThreadlocal();
    }

    public static void threadlocal() throws ExecutionException, InterruptedException {
        holder.set(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        Future<Object> future = executor.submit(() -> {
            System.out.println(holder.get());
            return "over";
        });
        System.out.println(future.get());
        holder.remove();
        shutdown(executor);
    }

    public static void inheritableThreadlocal() throws ExecutionException, InterruptedException {
        inheritableThreadLocal.set(1);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 1, 1000, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        Future<Object> firstFuture = executor.submit(() -> {
            try {
                inheritableThreadLocal.set(1);
                System.out.println("first task:"+inheritableThreadLocal.get());
                return null;
            }finally {
                inheritableThreadLocal.remove();
            }
        });
        inheritableThreadLocal.remove();
        inheritableThreadLocal.set(2);
        Future<Void> secondFuture = executor.submit(() -> {
            try {
                inheritableThreadLocal.set(2);
                System.out.println("second task:"+inheritableThreadLocal.get());
                return null;
            }finally {
                inheritableThreadLocal.remove();
            }
        });
        inheritableThreadLocal.remove();
        shutdown(executor);
    }

    private static void shutdown(ThreadPoolExecutor executor) {
        executor.shutdown();
        while (!executor.isTerminated()) {

        }
    }

}
