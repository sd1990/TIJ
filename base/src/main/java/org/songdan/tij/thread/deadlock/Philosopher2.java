package org.songdan.tij.thread.deadlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 另外一种方式实现哲学家进餐问题
 * 锁只有一个就是餐桌,当需要对哲学家进餐状态进行修改的时候加锁，并发度更高了，可以同时支持多个哲学家进餐
 * Created by PC on 2016/6/14.
 */
public class Philosopher2 implements Runnable {

    private ReentrantLock table;

    private Condition condition;

    private volatile boolean eating = false;

    private Philosopher2 right;

    private Philosopher2 left;

    private int i;

    public Philosopher2(ReentrantLock table, int i) {
        this.table = table;
        condition = table.newCondition();
        this.i = i;
    }

    public void setRight(Philosopher2 right) {
        this.right = right;
    }

    public void setLeft(Philosopher2 left) {
        this.left = left;
    }

    public void think() throws InterruptedException {
        table.lock();
        try {
            eating = false;
            System.out.println("哲学家" + i + "思考问题。。。");
            left.condition.signal();
            right.condition.signal();
        }
        finally {
            table.unlock();
        }
        Thread.sleep(500);
    }

    /**
     * 如果左边或右边的哲学家正在进餐，等待
     *
     * @throws InterruptedException
     */
    public void eat() throws InterruptedException {
        System.out.println("哲学家" + i + "准备进餐。。。");
        table.lock();
        try {
            while (right.eating || left.eating) {
                System.out.println("哲学家" + i + "不能进餐，需要等待。。。");
                condition.await();
            }
            System.out.println("哲学家" + i + "进餐中。。。");
            this.eating = true;
        }
        finally {
            table.unlock();
        }
        Thread.sleep(500);
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                eat();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock table = new ReentrantLock();
        Philosopher2[] philosopher2s = new Philosopher2[5];
        for (int i = 0; i < 5; i++) {
            philosopher2s[i] = new Philosopher2(table, i + 1);
        }
        for (int i = 0; i < philosopher2s.length; i++) {
            Philosopher2 philosopher = philosopher2s[i];
            philosopher.setLeft(philosopher2s[leftIndex(philosopher2s, i)]);
            philosopher.setRight(philosopher2s[rightIndex(philosopher2s, i)]);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < philosopher2s.length; i++) {
            executorService.execute(philosopher2s[i]);
        }
        TimeUnit.SECONDS.sleep(10);
        executorService.shutdownNow();
    }

    private static int leftIndex(Philosopher2[] philosopher2s, int i) {
        return (i + 1) % philosopher2s.length;
    }

    private static int rightIndex(Philosopher2[] philosopher2s, int i) {

        return (i + 4) % philosopher2s.length;
    }
}
