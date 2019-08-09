package org.songdan.tij.algorithm.recursive;

/**
 * @author: Songdan
 * @create: 2019-07-28 21:40
 **/
public class Fibonacci {

    public long recursive(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return recursive(n - 1) + recursive(n - 2);
    }

    public long noRecursive(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        long a = 1;
        long b = 1;
        long x = 0;
        for (int i = 3; i <= n; i++) {
            x = a + b;
            a = b;
            b = x;
        }
        return x;
    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        int num = 50;
        long start = System.currentTimeMillis();
        System.out.println(f.recursive(num));
        System.out.println(System.currentTimeMillis()-start);
        start = System.currentTimeMillis();
        System.out.println(f.noRecursive(num));
        System.out.println(System.currentTimeMillis()-start);
    }

}
