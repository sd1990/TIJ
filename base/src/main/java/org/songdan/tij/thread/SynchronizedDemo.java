package org.songdan.tij.thread;

/**
 * 此demo为了验证synchronize关键字，能够在继承体系中传递
 *
 * @author SONGDAN
 */
public class SynchronizedDemo {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        IntGenerator demo = new MySubGenerator();
        task.setDemo(demo);
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        Thread t3 = new Thread(task);
        Thread t4 = new Thread(task);
        Thread t5 = new Thread(task);
        Thread.sleep(100);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}

class Task implements Runnable {

    private IntGenerator demo;

    public void setDemo(IntGenerator demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        while (!demo.isCancel()) {
            int i = demo.next();
            System.out.println(Thread.currentThread().getName() + ":" + i);
            if ((i & 1) != 0) {
                System.out.println(Thread.currentThread().getName() + "dead ! i:" + i);
                demo.cancel();
            }
        }
    }

}

abstract class IntGenerator {

    private volatile boolean canceled = false;

    public boolean isCancel() {
        return canceled;
    }

    public void cancel() {
        canceled = true;
    }

    public abstract int next();
}

class MyGenerator extends IntGenerator {

    private int i = 0;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public synchronized int next() {
        i++;
        try {
            Thread.sleep(50);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        return i;
    }

}

/**
 * 完成继承父类的子类，调用父类的方法同步
 *
 * @author SONGDAN
 */
class MySubGenerator extends MyGenerator {

}

class MySubGenerator2 extends MyGenerator {

    private int i = 0;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    /**
     * 没有加上同步
     */
    @Override
    public int next() {
        i++;
        try {
            Thread.sleep(50);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        i++;
        return i;
    }
}