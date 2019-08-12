package org.songdan.tij.algorithm.math;

/**
 * 最大公约数
 *
 * @author: Songdan
 * @create: 2019-08-12 14:01
 **/
public class GCD {

    public static int gcdRecursive(int a, int b) {

        if (a < b) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        if (a % b == 0) {
            return b;
        }
        return gcdRecursive(b, a % b);
    }

    public static int gcd(int a, int b) {
        if (a < b) {
            a = a ^ b;
            b = a ^ b;
            a = a ^ b;
        }
        while (a % b > 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return b;
    }

    public static void main(String[] args) {
        System.out.println(gcdRecursive(10, 25));
        System.out.println(gcd(10, 25));
        System.out.println(gcd(10, 7));
        System.out.println(gcd(10, 3));
        System.out.println(gcd(15, 3));
    }

}
