package org.songdan.tij.algorithm.timewheel;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeWheelTest {

    @Test
    public void add() throws InterruptedException {
        synchronized (TimeWheelTest.class) {

            TimeWheel timeWheel = new TimeWheel();
            timeWheel.add(new DelayEvent(5));
            timeWheel.add(new DelayEvent(5));
            timeWheel.add(new DelayEvent(15));
            timeWheel.add(new DelayEvent(15));
            timeWheel.add(new DelayEvent(15));
            timeWheel.add(new DelayEvent(17));
            timeWheel.add(new DelayEvent(18));
            timeWheel.add(new DelayEvent(19));
            timeWheel.add(new DelayEvent(20));
            TimeWheelTest.class.wait(1000*30);
        }
    }

    public class DelayEvent implements Expiration {

        private int delaySecond;

        public DelayEvent(int delaySecond) {
            this.delaySecond = delaySecond;
        }

        @Override
        public void expired() {
            System.out.println("delay: "+delaySecond+" event working");

        }

        @Override
        public long getExpireTime() {
            return delaySecond;
        }

        @Override
        public int compareTo(Expiration o) {
            return (int) (this.getExpireTime()-o.getExpireTime());
        }

        @Override
        public String toString() {
            return "DelayEvent{" +
                    "delaySecond=" + delaySecond +
                    '}';
        }
    }

    @Test
    public void print() {
        System.out.println(" ......................阿弥陀佛......................\n"+
                "                       _oo0oo_                      \n"+
                "                      o8888888o                     \n"+
                "                      88\" . \"88                     \n"+
                "                      (| -_- |)                     \n"+
                "                      0\\  =  /0                     \n"+
                "                   ___/‘---’\\___                   \n"+
                "                  .' \\|       |/ '.                 \n"+
                "                 / \\\\|||  :  |||// \\                \n"+
                "                / _||||| -卍-|||||_ \\               \n"+
                "               |   | \\\\\\  -  /// |   |              \n"+
                "               | \\_|  ''\\---/''  |_/ |              \n"+
                "               \\  .-\\__  '-'  ___/-. /              \n"+
                "             ___'. .'  /--.--\\  '. .'___            \n"+
                "         .\"\" ‘<  ‘.___\\_<|>_/___.’>’ \"\".          \n"+
                "       | | :  ‘- \\‘.;‘\\ _ /’;.’/ - ’ : | |        \n"+
                "         \\  \\ ‘_.   \\_ __\\ /__ _/   .-’ /  /        \n"+
                "    =====‘-.____‘.___ \\_____/___.-’___.-’=====     \n"+
                "                       ‘=---=’                      \n"+
                "                                                    \n"+
                "....................佛祖保佑 ,永无BUG...................");
    }

    @Test
    public void test() {
        double a = 118308674l;
        System.out.println(a);

    }

}