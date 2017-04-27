package org.songdan.tij.thread;

/**
 * @author Songdan
 * @date 2017/4/27 16:27
 */
public class PossibleReordering {
    static  volatile int x = 0, y = 0;
    static volatile int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            one.join();
            other.join();
            if (x == 0 && y == 0) {
                System.out.println(i);
                break;
            }
            x = y = a = b = 0;
        }
    }
}
