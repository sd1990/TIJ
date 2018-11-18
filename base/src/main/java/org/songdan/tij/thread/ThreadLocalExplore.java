package org.songdan.tij.thread;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Songdan
 * @create: 2018-11-13 10:29
 **/
public class ThreadLocalExplore {

    public static void main(String[] args) throws InterruptedException {
        testGc();
//            testConcurrent();
    }

    private static void testGc() {
        Thread currentThread = Thread.currentThread();
        System.out.println(Demo.get());
        gc();
        //这个时候是gc回收不了thread.threadLocal中Entry弱引用关联的ThreadLocal,因为还有Demo.demo与之关联
        System.out.println(Demo.get());
        //去除Demo.demo与thread.threadLocal中Entry弱引用关联的ThreadLocal的关联
        Demo.demo = null;
        //这个时候是gc可以回收thread.threadLocal中Entry弱引用关联的ThreadLocal,但是回收不了Entry中的value
        gc();
        //这个时候将陈旧的Entry中的value设置为null
        System.out.println(Demo2.get());
//        memoryLeak();
//        System.out.println(currentThread);
//        gc();
        System.out.println(currentThread);
    }

    public static void testConcurrent() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(()->{
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                Demo3.increate();
                System.out.println(Demo3.demo.get().count);
            }
        }).start();
        new Thread(() -> {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 100; i++) {
                Demo3.increate();
                System.out.println(Demo3.demo.get().count);
            }
        }).start();
        countDownLatch.countDown();
    }

    private static void gc() {
        System.gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void memoryLeak() {
        ThreadLocal<Object> demo = new ThreadLocal<>();
        demo.set("hello world");
    }

    private static class Demo{
        private static ThreadLocal<Object> demo = new ThreadLocal<>();

        public static Object get() {
            demo.set("hello 11111");
            return demo.get();
        }

    }

    private static class Demo2{
        private static ThreadLocal<Object> demoA = new ThreadLocal<>();
        private static ThreadLocal<Object> demoB = new ThreadLocal<>();

        public static Object get() {
            demoA.set("hello 2222 AAAA");
            demoB.set("hello 2222 BBBB");
            return demoA.get();
        }

    }

    private static class Demo3{

        private static Counter counter = new Counter();

        private static ThreadLocal<Counter> demo = ThreadLocal.withInitial(() -> counter);

        public static void increate() {
            demo.get().increat();
        }
    }

    private static class Counter{
        private int count;

        public int increat() {
            return ++count;
        }
    }




}
