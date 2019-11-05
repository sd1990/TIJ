package org.songdan.tij.thread.executors;

import java.lang.reflect.Field;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: Songdan
 * @create: 2019-09-07 14:31
 **/
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {
        testConstructParams();
//        testCoreThreadBuild();
    }

    private static void testConstructParams() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.MILLISECONDS, new DecorateBlockingQueue<>(10),new CountThreadFactory("thread"));
        try {
            for (int i = 0; i < 110; i++) {
                executor.submit(new NamedTask());
            }
            executor.submit(new NamedTask());
        }finally {
            executor.shutdown();
            while (!executor.awaitTermination(10,TimeUnit.SECONDS)) {

            }
        }
    }

    private static void testCoreThreadBuild() throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 10, TimeUnit.MILLISECONDS, new DecorateBlockingQueue<>(10),new CountThreadFactory("thread"));
        executor.submit(new NamedTask());
        TimeUnit.SECONDS.sleep(5);
        executor.submit(new NamedTask());
        executor.shutdown();
        while (!executor.awaitTermination(10,TimeUnit.SECONDS)) {

        }
    }

    public static  class NamedTask implements Callable<Integer>{

        private static AtomicInteger count = new AtomicInteger(0);

        private int sequence = count.incrementAndGet();

        public NamedTask() {
            System.out.println("task_"+sequence+" bird");
        }



        @Override
        public String toString() {
            return "task_"+sequence;
        }

        @Override
        public Integer call() throws Exception {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sequence;
        }
    }

    public static class CountThreadFactory implements ThreadFactory{

        private AtomicInteger count = new AtomicInteger(0);

        private String name;

        public CountThreadFactory(String name) {
            this.name = name;
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r,this.name + "_" + count.incrementAndGet());
            thread.setDaemon(false);
            System.out.println(thread.getName()+" bird");
            return thread;
        }
    }

    public static class DecorateBlockingQueue<E> extends LinkedBlockingQueue<E> {

        private static Field callableField;

        static{
            try {
                callableField = FutureTask.class.getDeclaredField("callable");
                callableField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }

        public DecorateBlockingQueue(int size) {
            super(size);

        }

        @Override
        public boolean offer(E e) {
            boolean offer = super.offer(e);
            if (offer && FutureTask.class.isInstance(e)) {
                FutureTask futureTask = (FutureTask) e;
                Object callable;
                try {
                    callable = callableField.get(futureTask);
                    System.out.println("add queue " + callable);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
            return offer;
        }
    }

}
