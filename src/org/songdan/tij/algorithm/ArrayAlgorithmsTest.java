package org.songdan.tij.algorithm;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class ArrayAlgorithmsTest {

    private int[] arr = new int[10];

    @Before
    public void init() {
        fillArray(arr);
    }

    private void fillArray(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i]=random.nextInt(100);
        }
    }


    @Test
    public void testBubbleSort() throws Exception {
        Algorithms.ArrayAlgorithms.toString(arr);
        Algorithms.ArrayAlgorithms.bubbleSort(arr);
        Algorithms.ArrayAlgorithms.toString(arr);
    }

    @Test
    public void run() {
        System.out.println("hello world");
    }
}