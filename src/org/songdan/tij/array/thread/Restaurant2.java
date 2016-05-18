package org.songdan.tij.array.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用condition来完成线程的协作
 * Created by PC on 2016/5/18.
 */
public class Restaurant2 {

    Meal2 meal;

    WaitPerson2 waitPerson = new WaitPerson2(this);

    Chef2 chef = new Chef2(this);

    ExecutorService executorService = Executors.newCachedThreadPool();

    public Restaurant2() {
        executorService.execute(chef);
        executorService.execute(chef);
        executorService.execute(chef);
        executorService.execute(waitPerson);
        executorService.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant2();
    }
}

class Meal2 {

    int id;

    public Meal2(int i) {
        this.id = i;
    }

    @Override
    public String toString() {
        return "Meal2 "+id;
    }
}

class WaitPerson2 implements Runnable {

    Restaurant2 restaurant;

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    public WaitPerson2(Restaurant2 restaurant2) {
        this.restaurant = restaurant2;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try{
                    while (restaurant.meal == null) {
                        condition.await();
                    }
                }finally {
                    lock.unlock();
                }
                //消费
                restaurant.chef.lock.lock();
                try{
                    System.out.println("消费 "+restaurant.meal);
                    restaurant.meal = null;
                    restaurant.chef.condition.signalAll();
                }finally {
                    restaurant.chef.lock.unlock();
                }

            }
        }
        catch (InterruptedException e) {

        }
    }
}

/**
 * 厨师，生产者任务
 */
class Chef2 implements Runnable {

    Lock lock = new ReentrantLock();

    Condition condition = lock.newCondition();

    private Restaurant2 restaurant;

    public Chef2(Restaurant2 restaurant) {
        this.restaurant = restaurant;
    }

    private int count = 0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                lock.lock();
                try{
                    while (restaurant.meal != null) {
                        condition.await();
                    }
                }finally {
                    lock.unlock();
                }
                if (count==10) {
                    System.out.println("不再生产");
                    System.exit(0);
                }
                //消费
                restaurant.waitPerson.lock.lock();
                try{
                    restaurant.meal = new Meal2(++count);
                    System.out.println("生产 "+restaurant.meal);
                    restaurant.waitPerson.condition.signalAll();
                }finally {
                    restaurant.waitPerson.lock.unlock();
                }

            }
        }
        catch (InterruptedException e) {

        }
    }
}