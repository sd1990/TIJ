package org.songdan.tij.thread.visibility;

import java.util.concurrent.TimeUnit;

/**
 * 内存不可见
 *
 * @author Songdan
 * @date 2016/8/17
 */
public class NoVisibility {

    //    private static volatile boolean ready;
    private static boolean ready;

    private static int number;

    private static class ReaderThread extends Thread {

        @Override
        public void run() {
            while (!ready) {
                //                Thread.yield();
            }
            System.out.println(number);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        TimeUnit.MILLISECONDS.sleep(1000);
        System.out.println("wake up ...");
        number = 42;
        ready = true;
    }

}
