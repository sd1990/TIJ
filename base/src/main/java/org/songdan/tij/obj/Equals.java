package org.songdan.tij.obj;

import org.songdan.tij.arrays.ArrayUtils;

/**
 * @author: Songdan
 * @create: 2019-03-05 12:01
 **/
public class Equals {

    public static void nullEqual() {
        System.out.println(null == null);
    }

    public static void arrEuqal() {
        int[] arr1 = {1, 2, 3};
        int[] arr2 = {1, 2, 3};
        System.out.println(arr1==arr2);
    }

    public static void main(String[] args) {
        arrEuqal();
    }
}
