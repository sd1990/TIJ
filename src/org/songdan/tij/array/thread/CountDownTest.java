package org.songdan.tij.array.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 使用CountDownLatch实现一个线程等待另一个线程的功能
 * Created by PC on 2016/5/9.
 */
public class CountDownTest {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+": 哈哈，还是我先干活，我干完了才能轮到你");
                System.out.println(Thread.currentThread().getName()+": 哈哈，累了，我先睡一会，让别人等着吧");
                try {
                    Thread.sleep(3000);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(Thread.currentThread().getName()+": 睡醒了，接着干活吧");
                countDownLatch.countDown();
            }
        }).start();
        System.out.println(Thread.currentThread().getName() + ": 真不公平，我要等着别人干完才能到我");
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+":终于轮到我了");
        System.out.println(Thread.currentThread().getName()+":终于干完活了");
    }

}
