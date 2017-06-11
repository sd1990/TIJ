package org.songdan.tij.thread.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * 使用ForkJoin框架实现快排
 * 
 * @author Songdan
 * @date 2017/5/18 19:45
 */
public class QuickSortDemo {

    private final Random random = new Random();

    private ForkJoinPool forkJoinPool = new ForkJoinPool();

    private int[] generateArrays(int elements) {
        int[] array = new int[elements];
        for (int i = 0; i < elements; i++) {
            array[i] = random.nextInt(1000);
        }

        return array;
    }

    public void traditionalSort(int[] arr) {
        long start = System.currentTimeMillis();
        int left = 0;
        int right = arr.length - 1;
        quickSort(arr, left, right);
        System.out.println(System.currentTimeMillis()-start);

    }

    public void parallelSort(int[] arr) {
        long start = System.currentTimeMillis();
        ForkJoinTask<Void> task = forkJoinPool.submit(new QuickSortTask(arr, 0, arr.length - 1));
        task.join();
        System.out.println(System.currentTimeMillis()-start);
    }

    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left;
        int j = right;
        while (i != j) {
            for (; arr[j] >= arr[left] && j > i; j--) {

            }
            for (; arr[i] <= arr[left] && i < j; i++) {

            }
            if (i < j) {
                // 交换位置
                swap(arr, i, j);
            }
        }
        // 交换left和i
        swap(arr, left, i);
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        QuickSortDemo quickSortDemo = new QuickSortDemo();
        int[] arr = quickSortDemo.generateArrays(100);
        print(arr);
        quickSortDemo.traditionalSort(arr);
        print(arr);
        arr = quickSortDemo.generateArrays(100);
        print(arr);
        quickSortDemo.parallelSort(arr);
        print(arr);

    }

    private static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i);
            System.out.print(",");
        }
        System.out.println();
    }

    class QuickSortTask extends RecursiveAction {

        private int[] arr;

        private int left;

        private int right;

        public QuickSortTask(int[] arr, int left, int right) {
            this.arr = arr;
            this.left = left;
            this.right = right;
        }

        @Override
        protected void compute() {
            if (left >= right) {
                return;
            }
            int i = left;
            int j = right;
            while (i != j) {
                for (; arr[j] >= arr[left] && j > i; j--) {

                }
                for (; arr[i] <= arr[left] && i < j; i++) {

                }
                if (i < j) {
                    // 交换位置
                    swap(arr, i, j);
                }
            }
            // 交换left和i
            swap(arr, left, i);
            invokeAll(new QuickSortTask(arr, left, i - 1), new QuickSortTask(arr, i + 1, right));
        }
    }
}
