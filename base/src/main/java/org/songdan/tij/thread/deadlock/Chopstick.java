package org.songdan.tij.thread.deadlock;

/**
 * 哲学家思考问题中的筷子类，为共享资源
 * Created by PC on 2016/5/21.
 */
public class Chopstick {

    private static int counter = 0;

    private int id = counter++;

    private boolean token = false;

    public synchronized void take(Philosopher philosopher) throws InterruptedException {
//        if (token) {
//            wait();
//        }
        System.out.println(this + "被"+philosopher+"占有");
//        token = true;
    }

    public synchronized void drop(Philosopher philosopher) {
        System.out.println(this + "被"+philosopher+"释放");
//        token = false;
//        notifyAll();
    }

    @Override
    public String toString() {
        return "筷子_" + id;
    }
}
