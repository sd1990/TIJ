package org.songdan.tij.algorithm.sort;

/**
 * @author: Songdan
 * @create: 2019-03-10 20:27
 **/
public class InsertSort {

    private int[] arr;

    public void sort() {
        int length = arr.length;
        for (int i = 1; i < length; i++) {
            //在前面的数组中查找要插入的位置
            int value = arr[i];
            int k = i-1;
            for (; k >= 0; k--) {

                if (arr[k] > value) {
                    //向后移动
                    arr[k + 1] = arr[k];
                } else {
                    break;
                }

            }
            arr[k] = value;
        }
    }

}
