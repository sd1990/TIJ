package org.songdan.tij.thread.deadlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 资源死锁
 *
 * @author: Songdan
 * @create: 2020-06-14 22:47
 **/
public class Resource {

    private ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 5,
            30, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    public void test(int thread) {
        List<Future<Integer>> list = new ArrayList<>(thread);
        for (int i = 0; i < thread; i++) {
            Future<Integer> res = calculate();
            list.add(res);
        }
        for (Future<Integer> future : list) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    private void sleep() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Future<Integer> calculate() {
        Future<Integer> f = threadPool.submit(() -> {
            Future<Integer> f1 = threadPool.submit(() -> ThreadLocalRandom.current().nextInt(100));
            return f1.get() + ThreadLocalRandom.current().nextInt(100);
        });
        return f;
    }

    public static void main(String[] args) {
        Resource resource = new Resource();
        resource.test(3);
        resource.sleep();
        resource.threadPool.shutdown();
        while (!resource.threadPool.isTerminated()) {
        }
    }

}
