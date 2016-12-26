package org.songdan.tij;

import java.util.Arrays;
import java.util.Random;

/**
 * 冰激凌制造机器
 *
 * @author SONGDAN
 */
public class IceCream {

    private static Random rand = new Random(47);

    private static String[] FLAVORS =
            { "CHOCLATE", "STRAWBERRY", "Vanilla Fudge Swirl", "Mint Chip", "Mocha Almond Fudge", "Rum rasin",
                    "Mud Pie" };

    /**
     * @param n
     * @return
     */
    public static String[] flavorSet(int n) {
        if (n > FLAVORS.length) {
            throw new IllegalArgumentException("the number is big , can't set ");
        }
        String[] results = new String[n];
        boolean[] picked = new boolean[FLAVORS.length];
        for (int i = 0; i < n; i++) {
            int t;
            do
                t = rand.nextInt(FLAVORS.length);
            while (picked[t]);
            results[i] = FLAVORS[t];
            picked[t] = true;
        }
        return results;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 7; i++) {
            System.out.println(Arrays.deepToString(flavorSet(3)));

        }
    }

}
