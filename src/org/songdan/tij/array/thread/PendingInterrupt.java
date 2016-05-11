package org.songdan.tij.array.thread;


public class PendingInterrupt {
    public static void main(String[] args) {
        Thread.currentThread().interrupt();
        System.out.println("main run ...");
        try {
            Thread.sleep(1000l);
        }
        catch (InterruptedException e) {
            System.out.println("thread interrupt ..");
            e.printStackTrace();
        }
    }
}
