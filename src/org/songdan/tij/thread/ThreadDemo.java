package org.songdan.tij.thread;

public class ThreadDemo implements Runnable {
    
    
    
    public static void main(String[] args) {
        ThreadDemo td=new ThreadDemo();
        Thread t1=new Thread(td);
        t1.start();
        try {
            Thread.sleep(1000l);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("thread begin interrupt .. ");
        t1.interrupt();
    }

    @Override
    public void run() {
        try {
            System.out.println("thread run ...");
            Thread.sleep(10000l);
        }
        catch (InterruptedException e) {
            System.out.println("thread interrupted ... ");
            e.printStackTrace();
        }
    }
}
