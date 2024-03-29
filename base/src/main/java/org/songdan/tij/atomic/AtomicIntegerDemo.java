package org.songdan.tij.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * AtomicIntegerDemo
 * @author SONGDAN
 *
 */
public class AtomicIntegerDemo {
    
    static{
        System.out.println(Thread.currentThread().getContextClassLoader());
    }
    
    public AtomicIntegerDemo() {
        super();
        System.out.println("class loader is :"+this.getClass().getClassLoader());
    }
    public static void main(String[] args) {
        new AtomicIntegerDemo();
        AtomicInteger ai= new AtomicInteger(0);
        get(ai);
        ai.set(10);
        get(ai);
        ai.getAndSet(20);
        get(ai);
        ai.incrementAndGet();
        get(ai);
    }
    public static void get(AtomicInteger ai) {
        System.out.println(ai.get());
    }
}
