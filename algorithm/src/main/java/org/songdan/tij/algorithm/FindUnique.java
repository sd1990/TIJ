package org.songdan.tij.algorithm;

/**
 * 确定数组中只有一个不重复的元素，返回这个不重复的元素
 * @author: Songdan
 * @create: 2020-05-29 17:58
 **/
public class FindUnique {

    public int findUnique(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int rst = 0;
        for (int i = 0; i < arr.length; i++) {
            rst ^= arr[i];
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(new FindUnique().findUnique(new int[]{1, 2, 3, 2, 1, 3, 4, 4, 6}));
    }

}
