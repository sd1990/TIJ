package org.songdan.tij.thread;

/**
 * @author: Songdan
 * @create: 2019-06-11 20:00
 **/
public class ThreadInterrupt {

    public static void main(String[] args) {
        System.out.println("start");
        Thread.currentThread().interrupt();
        System.out.println("after interrupt");
        System.out.println(Thread.currentThread().isInterrupted());
    }

}
