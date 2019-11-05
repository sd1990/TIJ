package org.songdan.tij.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 26 二月 2018
 */
public class StreamDemo {

    public static void findFirsrt() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(null);
        Optional<Object> first = list.stream().findFirst();
    }

    public static void main(String[] args) {
//        findFirsrt();
        long s = System.currentTimeMillis();
        sortLimit();
        System.out.println(System.currentTimeMillis()-s);
    }

    public static void sortLimit() {
        Random random = new Random();
        int count = 5000000;
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = random.nextInt(count) * 2;
        }
        Arrays.stream(arr).sorted().limit(10).forEach(System.out::println);
    }

    public static void sort() {
        Random random = new Random();
        int count = 5000000;
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = random.nextInt(count) * 2;
        }
        Arrays.stream(arr).sorted();
    }

}
