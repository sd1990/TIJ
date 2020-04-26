package org.songdan.tij.algorithm.sort;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 中位数算法
 * @author: Songdan
 * @create: 2019-11-16 21:50
 **/
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(2)
public class MidNum {

    private Integer[] arr = getIntegers();

    @Benchmark
    public void find() {
        /*
        1. 建立两个堆，一个大顶堆，一个小顶堆，
        2. 小顶堆中堆中最小元素大于大顶堆中最大元素
        3. 两个堆大小相等
         */
        //小顶堆
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Comparator.naturalOrder());
        //大顶堆
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        pq1.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            Integer c = pq1.peek();
            if (arr[i].compareTo(c) > 0) {
                pq1.add(arr[i]);
            } else {
                pq2.add(arr[i]);
            }
        }

        // 调整两个堆直到大小相等
        while (Math.abs(pq1.size() - pq2.size()) > 1) {
            if (pq1.size() > pq2.size()) {
                pq2.add(pq1.poll());
            } else {
                pq1.add(pq2.poll());
            }
        }
        choiceMidNum(pq1, pq2);
    }

    @Benchmark
    public void findV2() {
        /*
        1. 建立两个堆，一个大顶堆，一个小顶堆，
        2. 小顶堆中堆中最小元素大于大顶堆中最大元素
        3. 两个堆大小相等
         */
        //小顶堆
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Comparator.naturalOrder());
        //大顶堆
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        pq1.add(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            Integer c = pq1.peek();
            if (arr[i].compareTo(c) > 0) {
                pq1.add(arr[i]);
            } else {
                pq2.add(arr[i]);
            }
            if (Math.abs(pq1.size() - pq2.size()) > 1) {
                //进行调整
                if (pq1.size() > pq2.size()) {
                    pq2.add(pq1.poll());
                } else {
                    pq1.add(pq2.poll());
                }
            }
        }


        choiceMidNum(pq1, pq2);
    }

    private Integer choiceMidNum(PriorityQueue<Integer> pq1, PriorityQueue<Integer> pq2) {
        Integer mid;
        if (arr.length % 2 == 0) {
            mid = pq1.peek();
        } else {
            PriorityQueue<Integer> pq = pq1;
            if (pq1.size()<pq2.size()) {
                pq = pq2;
            }
            mid = pq.peek();
        }
        return mid;
    }

    public static void main(String[] args) throws RunnerException {
        MidNum midNum = new MidNum();
        midNum.find();
        midNum.findV2();

        Options opts = new OptionsBuilder().include(MidNum.class.getSimpleName()).build();
        new Runner(opts).run();
    }

    private static Integer[] getIntegers() {
        int n = 100;
        List<Integer> arr = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            arr.add(new Integer(i + 1));
        }
        Collections.shuffle(arr);
        return arr.toArray(new Integer[0]);
    }

}
