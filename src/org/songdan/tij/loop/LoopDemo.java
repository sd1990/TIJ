package org.songdan.tij.loop;

/**
 * 循环
 * @author SONGDAN
 *
 */
public class LoopDemo {
    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] arr){
        int length = arr.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = 0; j < length-i-1; j++) {
                if(arr[j]>arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }
    /**
     * 选择排序
     * @param arr
     */
    public static void selectSort(int[] arr){
        int length = arr.length;
        for (int i = 0; i < length-1; i++) {
            for (int j = i+1; j < length; j++) {
                if (arr[i]>arr[j]) {
                    swap(arr, i, j);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        int[] arr={4,3,2,67,8,4,2,3,6,7,82,6,8};
//        selectSort(arr);
        bubbleSort(arr);
        printArray(arr);
    }

    private static void printArray(int[] arr) {
        for (int i : arr) {
            System.out.print(i);
            System.out.print(" ");
        }
        System.out.println();
    }
}
