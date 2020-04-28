package org.songdan.tij.codewars.sku_6;

/**
 *
 * see <a href="https://www.codewars.com/kata/541c8630095125aba6000c00">原题链接</a>
 * A digital root is the recursive sum of all the digits in a number. Given n, take the sum of the digits of n. If that value has more than one digit, continue reducing in this way until a single-digit number is produced. This is only applicable to the natural numbers.
 *
 * Here's how it works:
 *
 * digital_root(16)
 * => 1 + 6
 * => 7
 *
 * digital_root(942)
 * => 9 + 4 + 2
 * => 15 ...
 * => 1 + 5
 * => 6
 *
 * digital_root(132189)
 * => 1 + 3 + 2 + 1 + 8 + 9
 * => 24 ...
 * => 2 + 4
 * => 6
 *
 * digital_root(493193)
 * => 4 + 9 + 3 + 1 + 9 + 3
 * => 29 ...
 * => 2 + 9
 * => 11 ...
 * => 1 + 1
 * => 2
 *
 * @author: Songdan
 * @create: 2020-04-28 12:28
 **/
public class DRoot {

    public static int digital_root_for(int n) {
        while (n > 9 ) {
            n = n / 10 + n % 10;
        }
        return n;
    }

    public static int digital_root_for_1(int n) {
        while (n > 9) {
            int num = n;
            int sum = 0;
            while (num > 9) {
                sum += num % 10;
                num = num / 10;
            }
            n = sum;
        }
        return n;
    }

    public static int digital_root(int n) {
        if (n < 10) {
            return n;
        }
        return digital_root(digital_root(n / 10) + n % 10);
    }
}
