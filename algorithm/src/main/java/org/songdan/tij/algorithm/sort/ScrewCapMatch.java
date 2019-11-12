package org.songdan.tij.algorithm.sort;

import com.google.common.collect.Lists;
import org.songdan.tij.algorithm.ArraySortAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * N个螺丝和N个螺帽混在一堆，快速配对，一个螺丝只能匹配一个螺帽，一个螺帽也只能匹配一个螺丝。
 * 可以比较螺丝与螺帽，但是不能直接比较两个螺丝或两个螺帽
 *
 * @author: Songdan
 * @create: 2019-11-10 17:57
 **/
public class ScrewCapMatch {

    private int n;

    private Screw[] screws;

    private Cap[] caps;

    public ScrewCapMatch(int n) {
        this.n = n;
        this.screws = initScrew();
        this.caps = initCap();
    }

    public void match() {
        /**
         * 根据第一个螺丝，使用快速排序的思路，将比这个螺丝小的螺帽都放到左边，比这个螺丝大的螺帽都放到右边，
         * 然后把匹配的螺帽放到i，j交界的地方，再通过匹配到到螺帽将螺丝进行分组
         *
         */
        matchInner(0, n - 1);
    }

    private void matchInner(int left, int right) {
        if (left >= right) {
            return;
        }
        Screw screw = screws[left];
        int m = choice(left, right, screw,caps);
        Cap cap = caps[m];
        int n = choice(left, right, cap,screws);
        System.out.println("m = " + m + ", n = " + n);
        print();

        matchInner(left, m - 1);
        matchInner(m + 1, right);

    }

    private int choice(int left, int right, Comparable c,Comparable[] arr) {
        int sCount = 0;
        int bCount = 0;

        Comparable[] small = new Comparable[right - left + 1];
        Comparable[] big = new Comparable[right - left + 1];
        Comparable target = null;
        for (int i = left; i <= right; i++) {
            if (arr[i].compareTo(c) < 0) {
                small[sCount++] = arr[i];
            } else if (arr[i].compareTo(c) > 0) {
                big[bCount++] = arr[i];
            } else {
                target = arr[i];
            }
        }
        //拼接数组
        int i = left;
        for (int j = 0; j < sCount; j++) {
            arr[i++] = small[j];
        }
        arr[i++] = target;
        for (int j = 0; j < bCount; j++) {
            arr[i++] = big[j];
        }
        return left + sCount;
    }


    private Screw[] initScrew() {
        List<Screw> screws = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            screws.add(new Screw(i + 1));
        }
        Collections.shuffle(screws);
        return screws.toArray(new Screw[0]);
    }

    private Cap[] initCap() {
        List<Cap> caps = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            caps.add(new Cap(i + 1));
        }
        Collections.shuffle(caps);
        return caps.toArray(new Cap[0]);
    }

    public static void main(String[] args) {
        ScrewCapMatch screwCapMatch = new ScrewCapMatch(20);
        screwCapMatch.print();
        screwCapMatch.match();
        screwCapMatch.print();
    }

    private void print() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            builder.append(caps[i].size).append("-");
        }
        builder.append(System.lineSeparator());
        for (int i = 0; i < n; i++) {
            builder.append(screws[i].size).append("-");
        }
        builder.append(System.lineSeparator());
        System.out.println(builder.toString());
    }


    /**
     * 螺丝
     */
    private static class Screw implements Comparable<Cap>{

        private int size;

        public Screw(int size) {
            this.size = size;
        }

        public int match(Cap cap) {
            return this.size - cap.size;
        }

        @Override
        public String toString() {
            return "Screw{" +
                    "size=" + size +
                    '}';
        }

        @Override
        public int compareTo(Cap o) {
            return match(o);
        }
    }

    /**
     * 螺帽
     */
    private static class Cap implements Comparable<Screw>{

        private int size;

        public Cap(int size) {
            this.size = size;
        }

        public int match(Screw screw) {
            return this.size - screw.size;
        }

        @Override
        public String toString() {
            return "Cap{" +
                    "size=" + size +
                    '}';
        }

        @Override
        public int compareTo(Screw screw) {
            return match(screw);
        }
    }


}
