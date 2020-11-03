package org.songdan.tij.algorithm.string;

/**
 * 求最大公共子串,使用动态规划
 *   a b c d e
 * a 1 0 0 0 0
 * b 0 2 0 0 0
 * c 0 0 3 0 0
 * @author: Songdan
 * @create: 2020-06-19 18:54
 **/
public class MaxSubStr {

    public static void main(String[] args) {
        System.out.println(maxStr("abc","abc"));
        System.out.println(maxStr("abc","a"));
        System.out.println(maxStr("abc","ab"));
        System.out.println(maxStr("abc","c"));
        System.out.println(maxStr("abcdf","bdf"));
    }

    public static String maxStr(String str1, String str2) {
        char[] ch1 = str1.toCharArray();
        char[] ch2 = str2.toCharArray();
        int[][] arr = new int[ch1.length][ch2.length];
        int max = 0;
        int m = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (ch1[i] == ch2[j]) {
                    if (i - 1 < 0 || j - 1 < 0) {
                        arr[i][j] = 1;
                    } else {
                        arr[i][j] = arr[i - 1][j - 1] + 1;
                    }
                    max = Math.max(max, arr[i][j]);
                    m = i - max+1;
                } else {
                    arr[i][j] = 0;
                }
            }
        }
        if (max > 0) {
            return str1.substring(m, m + max);
        }
        return "";
    }

}
