package org.songdan.tij.array.thread;

/**
 * 发射之前的倒计时
 * @author Songdan
 * @date 2016/5/11
 */
public class LiftOff implements Runnable{

    private static int count = 0;

    private final int id = count++;

    private int countDown = 10;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown == 0 ? "LiftOff" : countDown) + ")";
    }

    @Override
    public void run() {
        while (countDown-- != 0) {
            System.out.println(status());
            Thread.yield();
        }
    }
}
