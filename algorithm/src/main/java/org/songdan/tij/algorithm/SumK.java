package org.songdan.tij.algorithm;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: Songdan
 * @create: 2019-10-31 22:18
 **/
public class SumK {

    public static int twoSum(int[] arr,int target) {
        Arrays.sort(arr);
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int j = Arrays.binarySearch(arr, target - arr[i]);
            if (j > i) {
                count++;
            }
        }
        return count;
    }

    private static int[] initArr(int i) {
        int[] arr = new int[i];
        Random random = new Random();
        for (int j = 0; j < i; j++) {

            int val = random.nextInt(10);
            if ((j & 1) == 0) {
                arr[j] = -1 * val;
            } else {
                arr[j] = val;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = initArr(10);
        System.out.println(Arrays.toString(arr));
        System.out.println(twoSum(arr,0));
    }

}
