package org.songdan.tij.algorithm;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * 约瑟夫环问题
 *
 * @author: Songdan
 * @create: 2019-08-18 10:26
 **/
public class JosephCircle {

    public static int calculate(int n, int m) {
        int[] arr = new int[n];
        int index = 0;
        int count = 0;
        while (validCount(arr) > 1) {
            if (arr[index % arr.length] != -1) {
                count++;
            }
            if (count == m) {
                arr[index % arr.length] = -1;
                count = 0;
            }
            index++;
        }
        while (arr[index % arr.length] == -1) {
            index++;
        }
        return index % arr.length+1;
    }

    private static int validCount(int[] arr) {
        int count = 0;
        for (int i : arr) {
            if (i >= 0) {
                count++;
            }
        }
        return count;
    }

    public static int calculateRecusive(int n, int m) {
        if (n == 1) {
            return 1;
        }
        /**
         * 1. 递归终止条件
         * 2. 提取递归公式 这里的要提取的时删除前编号和删除后编号的映射关系
         * https://mp.weixin.qq.com/s/kiJsRwQdZxTGdFuboOWHmA
         */
        return ((calculateRecusive(n - 1, m) + m - 1) % n) + 1;
    }

    public static void main(String[] args) {
//        System.out.println(calculate(6, 1));
        System.out.println(calculate(6, 2));
        System.out.println(calculateRecusive(6, 2));
    }

}
