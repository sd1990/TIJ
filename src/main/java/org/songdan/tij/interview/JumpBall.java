package org.songdan.tij.interview;

/**
 * 一个球从指定的高度落下，每次落地后反跳到原高度的一半在落下
 * Created by SongDan on 2017/2/16.
 */
public class JumpBall {

    private double high;

    public JumpBall(double high) {
        this.high = high;
    }

    public JumpBall() {

    }

    public double fall() {
        System.out.println("落下的高度为：" + high);
        return high = high / 2;
    }

    public double sumHigh2(int n) {
        double sumHigh = high;
        for (int i = 1; i < n; i++) {
            sumHigh+=fall()*2;
        }
        return sumHigh;
    }

    public double sumHigh1(int n) {
        double sumHigh = 0;
        int i = 1;
        while (i<=n) {
            if (i==1) {
                sumHigh += high;
            } else{
                sumHigh += fall()*2;
            }
            i++;
        }
        return sumHigh;
    }

    public double sumHigh(int n) {
        double sumHigh = high*-1;
        for (int i = 0; i < n; i++) {
            sumHigh+=high*2;
            fall();
        }
        return sumHigh;
    }

    public static void main(String[] args) {
//        System.out.println(new JumpBall(100).sumHigh(1));
//        System.out.println(new JumpBall(100).sumHigh(2));
//        System.out.println(new JumpBall(100).sumHigh(3));
//        System.out.println(new JumpBall(100).sumHigh(4));
//        System.out.println(new JumpBall(100).sumHigh(5));
//        System.out.println(new JumpBall(100).sumHigh(6));
//        System.out.println(new JumpBall(100).sumHigh(7));
//        System.out.println(new JumpBall(100).sumHigh(8));
//        System.out.println(new JumpBall(100).sumHigh(9));
        System.out.println(new JumpBall(100).sumHigh(10));
    }
    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }
}
