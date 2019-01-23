package org.songdan.tij.algorithm.sort;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 堆排序
 * 1. 创建堆
 * 2. 调整堆
 *
 * @author: Songdan
 * @create: 2019-01-23 19:02
 **/
public class HeapSort {

    private int[] arr;

    public HeapSort(int length) {
        arr = new int[length];
    }

    public static void main(String[] args) {
        int length = 10;
        HeapSort heapSort = new HeapSort(length);
        heapSort.initArr();
    }

    private void sort() {
        HeapSort heapSort = this;
        System.out.println("before init heap   :" + this);
        heapSort.createHeap();
        System.out.println("after create heap  :" + heapSort);
        //开始调整
        int swapIndex = heapSort.arr.length - 1;
        for (; swapIndex >= 0; swapIndex--) {
            heapSort.swap(0, swapIndex);
            System.out.println("swap index :" + swapIndex);
            heapSort.shiftDown(0, swapIndex);
            System.out.println("swap index :" + heapSort);
        }
        System.out.println("after adjust heap  :" + heapSort);
    }

    private void shiftDown(int index, int swapIndex) {
        int leftSon = 2 * index + 1;
        int rightSon = leftSon + 1;
        int compareIndex = leftSon;
        if (leftSon >= swapIndex) {
            return;
        }
        if (rightSon<swapIndex) {
            //获取左右孩子节点的较大值
            if (arr[leftSon] < arr[rightSon]) {
                compareIndex = rightSon;
            }
        }
        //如果小于孩子节点，进行交换，并继续下移
        if (arr[index] < arr[compareIndex]) {
            swap(index, compareIndex);
            shiftDown(compareIndex, swapIndex);
        }


    }

    private void initArr() {
        ThreadLocalRandom localRandom = ThreadLocalRandom.current();

        for (int i = 0; i <= arr.length - 1; i++) {
            arr[i] = localRandom.nextInt(arr.length * 10);
        }
    }

    private void createHeap() {
        for (int i = (arr.length - 1) / 2; i >= 0; i--) {
            shiftDown(i, arr.length);
        }
    }

    private void shiftDown(int index) {
        if (index * 2 > arr.length - 1) {
            return;
        }
        int leftSon = 2 * index + 1;
        int rightSon = leftSon + 1;
        int compareIndex = leftSon;
        if (rightSon <= arr.length - 1) {
            //获取左右孩子节点的较大值
            if (arr[leftSon] < arr[rightSon]) {
                compareIndex = rightSon;
            }
        }
        //如果小于孩子节点，进行交换，并继续下移
        if (arr[index] < arr[compareIndex]) {
            swap(index, compareIndex);
            shiftDown(compareIndex);
        }
    }

    private void shiftUp(int index) {
        if (index == 0) {
            return;
        }
        int pIndex = (index - 1) / 2;
        if (arr[index] > arr[pIndex]) {
            swap(index, pIndex);
            shiftUp(pIndex);
        }
    }

    private void swap(int index, int pIndex) {
        int temp = arr[index];
        arr[index] = arr[pIndex];
        arr[pIndex] = temp;
    }

    @Override
    public String toString() {
        return Arrays.toString(arr);
    }


}
