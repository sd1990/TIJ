package org.songdan.tij.algorithm.sort;

import java.util.Arrays;

/**
 * @author: Songdan
 * @create: 2019-05-25 15:08
 **/
public class Heap {

    private boolean bigHeap;

    public Heap(boolean bigHeap) {
        this.bigHeap = bigHeap;
    }

    /**
     * 数据堆排序
     *
     * @param arr
     */
    public void sort(int[] arr, int n) {
        //建立大顶堆，从第一个非叶子节点开始，自上而下进行堆化
        createHeap(arr, n);
        System.out.println("after create Heap:" + Arrays.toString(arr));
        int k = n;
        while (k > 0) {
            //堆顶元素和最后一个元素交换
            swap(arr, 0, k);
            k--;
            //迭代堆化
            heapify(arr, k, 0);
        }

    }

    private void createHeap(int[] arr, int n) {
        int i = getParent(n);
        while (i >= 0) {
            heapify(arr, n, i);
            i--;
        }
    }

    /**
     * 自上而下堆化
     *
     * @param arr
     * @param n
     * @param i
     */
    private void heapify(int[] arr, int n, int i) {
        //循环条件，i是非叶子节点
        while (true) {
            //比较左右子节点的值
            int index = i;
            if (bigHeap) {
                if (getLeft(i) <= n && arr[index] < arr[getLeft(i)]) {
                    index = getLeft(i);
                }
                if (getRight(i) <= n && arr[index] < arr[getRight(i)]) {
                    index = getRight(i);
                }
            } else {
                if (getLeft(i) <= n && arr[index] > arr[getLeft(i)]) {
                    index = getLeft(i);
                }
                if (getRight(i) <= n && arr[index] > arr[getRight(i)]) {
                    index = getRight(i);
                }
            }
            if (index != i) {
                swap(arr, i, index);
                //修改循环变量，继续递归
                i = index;
            } else {
                //没有发生变化，说明满足堆的定义
                break;
            }
        }
    }

    private int getLeft(int i) {
        return 2 * i + 1;
    }

    private int getRight(int i) {
        return 2 * i + 2;
    }

    private int getParent(int i) {
        return (i - 1) / 2;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {7, 5, 19, 8, 4, 1, 20, 13, 16};
        new Heap(true).sort(arr, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

}
