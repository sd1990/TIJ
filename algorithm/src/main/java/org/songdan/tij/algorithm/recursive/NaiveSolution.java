package org.songdan.tij.algorithm.recursive;

/**
 * Given a rod of length n and an array that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 * @author: Songdan
 * @create: 2019-07-29 21:49
 **/
public class NaiveSolution {

    static int getValue(int[] values, int length) {
        if (length <= 0) {
            return 0;
        }
        int tmpMax = -1;
        for (int i = 0; i < length; i++) {
            tmpMax = Math.max(tmpMax, values[i] + getValue(values, length - i - 1));
        }
        return tmpMax;
    }

    public static void main(String[] args) {
        int[] values = new int[]{3, 7, 1, 3, 9};
        int rodLength = values.length;

        System.out.println("Max rod value: " + getValue(values, rodLength));
    }

}
