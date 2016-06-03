package org.songdan.tij.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 汽车保养有两个过程，打蜡和抛光
 * 抛光必须在打蜡后，而打蜡任务在涂另一层蜡之前，必须等待抛光任务完成
 * Created by PC on 2016/4/15.
 */
public class WaxOMatic {

    public static void main(String[] args) {
        Car car = new Car();
        WaxOn waxOn = new WaxOn(car);
        WaxOff waxOff = new WaxOff(car);
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(waxOff);
        executorService.submit(waxOn);
        try {
            TimeUnit.SECONDS.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdownNow();
    }
}

class Car{

    private boolean waxOn = false;

    /**
     * 打蜡任务，实际上就是把waxOn的状态更改为true
     */
    public synchronized void waxOn() {
        waxOn = true;
        //唤醒在这个对象锁上阻塞的任务
        notifyAll();
    }

    /**
     * 抛光任务
     */
    public synchronized void buffed() {
        waxOn = false;
        notifyAll();
    }

    /**
     * 等待打蜡结束
     */
    public synchronized void waitForWaxing() {
        while (!waxOn)//只要一直没有完成打蜡，就等待
            try {
                wait();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    /**
     * 等待抛光结束
     * @throws InterruptedException
     */
    public synchronized void waitForBuffering() throws InterruptedException {
        while (waxOn)//只要一直没有完成抛光，就等待
            wait();
    }

}

/**
 * 打蜡任务
 */
class WaxOn implements Runnable {

    private Car car;

    public WaxOn(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                System.out.println("wax on ...");
                TimeUnit.MILLISECONDS.sleep(300);
                car.waxOn();
                System.out.println("wait for buff");
                car.waitForBuffering();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 抛光任务
 */
class WaxOff implements Runnable {

    private Car car;

    public WaxOff(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                car.waitForWaxing();
                System.out.println("wax off ...");
                TimeUnit.MILLISECONDS.sleep(300);
                car.buffed();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}