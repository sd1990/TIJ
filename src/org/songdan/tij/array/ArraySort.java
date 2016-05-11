package org.songdan.tij.array;

import java.util.Random;

/**
 * 常见的数组排序
 */
public class ArraySort {

    /**
     * 冒泡排序
     *
     * @param arr
     */
    public static void bubble(int[] arr) {
        //控制内层遍历的次数
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
            System.out.println(arr[i]);
        }
    }

    /**
     * 选择排序
     *
     * @param arr
     */
    public static void select(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    swap(arr, i, j);
                }
            }
            System.out.println(arr[i]);
        }
    }

    /**
     * 快速排序
     *
     * @param arr
     */
    public static void quickSort(int[] arr, int left, int right) {
        if(left>=right){
            return ;
        }
        int i = left;
        int j = right;
        while (j != i) {
            for (; i < j && arr[j] >= arr[left]; j--) {
                continue;
            }
            for (; i < j && arr[i] <= arr[left]; i++) {
                continue;
            }
            if (i < j){
                swap(arr,i,j);
            }
        }
        swap(arr,left,i);
        quickSort(arr,left,i-1);
        quickSort(arr,i+1,right);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    public static int[] buildArray(int length, boolean random) {
        int[] arr = new int[length];
        if (random) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (int) (Math.random() * 10);
            }
        }
        else {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i - new Random().nextInt(length);
            }
        }
        return arr;
    }

    public static String toString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append("--");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] arr = buildArray(10, true);
        System.out.println(toString(arr));
//      bubble(arr);
//      select(arr);
        quickSort(arr,0,arr.length-1);
        System.out.println(toString(arr));
    }
}
