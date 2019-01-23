package org.songdan.tij.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 大数据量排序
 * @author: Songdan
 * @create: 2019-01-23 21:43
 **/
public class BigDataSort {

    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int[] arr = new int[500000];
        for (int i = 0; i < 500000; i++) {
            arr[i] = i*2;
        }
        int[] heapArr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            heapArr[i] = arr[i];
        }
        createHeap(heapArr);
        for (int i = 1000; i < 500000; i++) {
            if (arr[i]>heapArr[0]) {
                heapArr[0] = arr[i];
                shiftDown(0,heapArr);
            }
        }
        System.out.println(Arrays.toString(heapArr));
    }

    private static void createHeap(int[] arr) {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            shiftDown(i, arr);
        }
    }

    private static void shiftDown(int index,int[] arr) {
        while (2 * index + 1<arr.length) {
            int leftSon = 2 * index + 1;
            int compareIndex = leftSon;
            int rightSon = leftSon + 1;
            if (rightSon < arr.length) {
                if (arr[leftSon] > arr[rightSon]) {
                    compareIndex = rightSon;
                }
            }
            //如果大于最小的子节点，进行交换，并继续下移
            if (arr[index] > arr[compareIndex]) {
                swap(index, compareIndex, arr);
                index = compareIndex;
            } else {
                break;
            }
        }


    }

    private static void swap(int index, int pIndex,int[] arr) {
        int temp = arr[index];
        arr[index] = arr[pIndex];
        arr[pIndex] = temp;
    }


}
