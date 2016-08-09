package org.songdan.tij.algorithm;

import java.util.Random;

/**
 * 算法集合
 * @author Songdan
 * @date 2016/8/9
 */
public class Algorithms {

    /**
     * 数组算法
     */
    static class ArrayAlgorithms{

        /**
         * 冒泡排序
         * @param arr
         */
        public static void bubbleSort(int[] arr) {
            //外层控制遍历次数
            for (int i = 0; i < arr.length-1; i++) {
                //内存控制每次遍历的比较次数,每次都是从第一个开始，
                for (int j = 0; j < arr.length-1-i; j++) {
                    if (arr[j]>arr[j+1]) {
                        swap(arr, j, j + 1);
                    }
                }
            }
        }

        /**
         * 选择算法
         * @param arr
         */
        public static void selectSort(int[] arr) {
            //外层控制每次遍历的元素，最先确定第一个元素，第二次从第二个元素开始比较
            for (int i = 0; i < arr.length-1; i++) {
                //内层控制每次遍历比较的元素
                for (int j = i+1; j <arr.length ; j++) {
                    if (arr[i] > arr[j]) {
                        swap(arr,i,j);
                    }
                }
            }
        }

        /**
         * 快速排序，分治算法
         * @param arr
         */
        public static void quickSort(int[] arr) {
            int left = 0;
            int right = arr.length - 1;
            quickSortInner(arr, left, right);
        }

        private static void quickSortInner(int[] arr, int left, int right) {
            if (left >= right) {
                return ;
            }
            int i = left;
            int j = right;
            while (i != j) {
                for (; i < j && arr[j] >= arr[left]; j--) {

                }
                for (; i < j && arr[i] <= arr[left]; i++) {

                }
                if (i < j) {
                    swap(arr, j, i);
                }
            }
            swap(arr,left,i);
            quickSortInner(arr, left, i - 1);
            quickSortInner(arr,i+1,right);
        }

        private static void swap(int[] arr, int j, int i) {
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }

        public static String toString(int[] arr) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {

                if (i == arr.length -1) {
                    builder.append(arr[i]);
                }else {
                    builder.append(arr[i]).append(",");
                }
            }
            return builder.toString();
        }

        private static void fillArray(int[] arr) {
            Random random = new Random();
            for (int i = 0; i < arr.length; i++) {
                arr[i]=random.nextInt(100);
            }
        }

        public static void main(String[] args) {
            int[] arr = new int[10];
            fillArray(arr);
            System.out.println(toString(arr));
            long startTime = System.nanoTime();
//            bubbleSort(arr);
//            selectSort(arr);
            quickSort(arr);
            System.out.println("time : "+(System.nanoTime()-startTime));
            System.out.println(toString(arr));
        }
    }

}
