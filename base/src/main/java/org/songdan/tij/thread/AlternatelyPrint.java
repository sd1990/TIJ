package org.songdan.tij.thread;

/**
 * 2个线程交替打印
 *
 * @author: Songdan
 * @create: 2020-08-03 11:23
 **/
public class AlternatelyPrint {

    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();
        NumberHolder numberHolder = new NumberHolder(2);
        new Thread(new Worker(lock, 0, numberHolder)).start();
        new Thread(new Worker(lock, 1, numberHolder)).start();
        synchronized (numberHolder) {
            numberHolder.wait();
        }
        System.out.println("我打印完了");
    }

    static class NumberHolder{
        private volatile int num;

        public NumberHolder(int num) {
            this.num = num;
        }
    }



    static class Worker implements Runnable{

        private final Object lock;

        private int num;

        private NumberHolder numberHolder;

        public Worker(Object lock, int num, NumberHolder numberHolder) {
            this.lock = lock;
            this.num = num;
            this.numberHolder = numberHolder;
        }

        @Override
        public void run() {
            while (num < 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread() + ":" + num);
                    lock.notify();
                    num += 2;
                    if (num < 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e.getCause());
                        }
                    }
                }
            }
            System.out.println(Thread.currentThread() + ":" + "我打印完了");
            synchronized (numberHolder) {
                if (--numberHolder.num == 0) {
                    numberHolder.notify();
                }
            }

        }
    }


}
