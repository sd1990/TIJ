package org.songdan.tij.thread.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 哲学家类，任务类
 * Created by PC on 2016/5/21.
 */
public class Philosopher implements Runnable {

    private static int counter = 0;

    private int id = counter++;

    private Chopstick left;

    private Chopstick right;

    private int ponder;

    public Philosopher(Chopstick left, Chopstick right, int ponder) {
        this.left = left;
        this.right = right;
        this.ponder = ponder;
    }

    private static Random random = new Random(47);

    public void thinking() throws InterruptedException {
        if (ponder<=0) {
            return;
        }
        TimeUnit.MILLISECONDS.sleep(random.nextInt(ponder));
    }

    @Override
    /**
     * 描述哲学家这个任务是干什么的
     */
    public void run() {
        try {
            while (!Thread.interrupted()){
                System.out.println(this+"要思考了。。。");
//                thinking();
                System.out.println(this + "要开始拿筷子。。。");
                left.take();
                right.take();
                System.out.println(this + "又要思考了。。。");
//                thinking();
                System.out.println(this+"释放筷子了。。。");
                left.drop();
                right.drop();
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public String toString() {
        return "哲学家_"+id;
    }
}
