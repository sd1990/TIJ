package org.songdan.tij.stream;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.IntConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        findFirsrt();
//        long s = System.currentTimeMillis();
//        sortLimit();
//        System.out.println(System.currentTimeMillis()-s);
//        System.out.println(IntStream.range(1,1000).parallel().reduce((x1,x2)->x1+x2).getAsInt());
//        ForkJoinPool forkJoinPool = new ForkJoinPool();
//        ForkJoinTask<Integer> task = forkJoinPool.submit(() -> {
//            return IntStream.range(1, 1000).parallel().reduce((x1, x2) -> x1 + x2).getAsInt();
//        });
//        task.get();
        sortLimitV2();

    }

    public static void sortLimit() {
        Random random = new Random();
        int count = 5;
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = random.nextInt(count) * 2;
        }
        Arrays.stream(arr).sorted().limit(10).forEach(System.out::println);
    }

    public static void sortLimitV2() {
        Random random = new Random();
        int count = 5;
        List<Integer> source = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            source.add(random.nextInt(count) * 2);
        }
        System.out.println(source.stream().sorted().limit(10).collect(Collectors.toList()));

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
