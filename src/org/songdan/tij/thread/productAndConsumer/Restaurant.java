package org.songdan.tij.thread.productAndConsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal {

    private int orderNum;

    public Meal(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String toString() {
        return "meal is " + orderNum;
    }
}

class Waiter implements Runnable {

    private Restaurant restaurant;

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                System.out.println("waiter got the meal :" + restaurant.meal);
                // got the meal
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        }
        catch (InterruptedException e) {
            System.out.println("thread wait interrupted");
        }
    }
}

class Chef implements Runnable {

    private Restaurant restaurant;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    private int count = 0;

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null) {
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
public class Restaurant {

    Meal meal;

    Waiter waiter = new Waiter(this);

    Chef chef = new Chef(this);

    ExecutorService executor = Executors.newCachedThreadPool();

    public Restaurant() {
        executor.submit(waiter);
        executor.submit(chef);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
