package org.songdan.tij.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorsDemo {
    
    public static void main(String[] args) {
        System.out.println("hello world");
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            newCachedThreadPool.execute(new MyTask());
            System.out.println("**************         "+i+"  **********************");
        }
        
    }
    
    public static class MyTask implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":run ....");
        }
        
    }
    
}
