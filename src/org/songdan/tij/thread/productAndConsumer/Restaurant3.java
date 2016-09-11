package org.songdan.tij.thread.productAndConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class BusBoy implements Runnable {

    private Restaurant3 restaurant;

    boolean notify = false;

    public Meal meal;

    public BusBoy(Restaurant3 restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (!notify) {
                        System.out.println("busboy : wait for waiter notified");
                        wait();
                    }
                    notify = false;
                }
                System.out.println("busboy got the meal :"+meal);
                synchronized (restaurant.waiter) {
                    restaurant.waiter.notified = true;
                    restaurant.waiter.notifyAll();
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println("thread wait interrupted");
        }
    }
}
class Waiter3 implements Runnable {

    private Restaurant3 restaurant;

    boolean notified = false;

    public Waiter3(Restaurant3 restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        System.out.println("waiter : wait for chef notify");
                        wait();
                    }
                }
                System.out.println("waiter got the meal :"+restaurant.meal);
                synchronized (restaurant.busBoy) {
                    restaurant.busBoy.notify = true;
                    restaurant.busBoy.meal = restaurant.meal;
                    restaurant.busBoy.notifyAll();
                }
                // got the meal
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
                synchronized (this) {
                    while (!notified) {
                        System.out.println("wait for busboy notified");
                        wait();
                    }
                    notified = false;
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println("thread wait interrupted");
        }
    }
}

class Chef3 implements Runnable {

    private Restaurant3 restaurant;

    public Chef3(Restaurant3 restaurant) {
        this.restaurant = restaurant;
    }

    private int count = 0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
                        System.out.println("chef : wait for waiter consume");
                        wait();
                    }
                }
                if (++count == 10) {
                    System.out.println("out of cooking , close");
                    restaurant.executor.shutdownNow();
                }
                System.out.println("order up ...");
                synchronized (restaurant.waiter) {
                    restaurant.meal = new Meal(count);
                    System.out.println("chef : notify the waiter");
                    restaurant.waiter.notifyAll();
                }
                TimeUnit.SECONDS.sleep(2);
            }
        }
        catch (InterruptedException e) {
            System.out.println("thread chef interrupted");
        }

    }
}

/**
 * Created by PC on 2016/4/16.
 */
public class Restaurant3 {

    Meal meal;

    Waiter3 waiter = new Waiter3(this);

    Chef3 chef = new Chef3(this);

    BusBoy busBoy = new BusBoy(this);

    ExecutorService executor = Executors.newCachedThreadPool();

    public Restaurant3() {
        executor.submit(waiter);
        executor.submit(chef);
        executor.submit(busBoy);
    }

    public static void main(String[] args) {
        new Restaurant3();
    }
}
