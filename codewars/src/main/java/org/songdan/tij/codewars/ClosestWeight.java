package org.songdan.tij.codewars;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ClosestWeight {

    public static int[][] closest(String strng) {
        if ("".equals(strng.trim())) {
            return new int[0][0];
        }
        return find(weight(split(strng))).toArray();
    }

    private static List<Integer> split(String strng) {
        return Stream.of(strng.split("(\\s)+")).map(Integer::parseInt).collect(Collectors.toList());
    }

    private static List<RichNumber> weight(List<Integer> numbers) {
        ArrayList<RichNumber> richNumbers = new ArrayList<>(numbers.size());
        for (int i = 0; i < numbers.size(); i++) {
            richNumbers.add(build(i, numbers.get(i)));
        }
        return richNumbers;
    }

    private static Pair find(List<RichNumber> numbers) {
        Pair closest = new Pair(numbers.get(0), numbers.get(1));
        for (int i = 0; i < numbers.size() - 1; i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                Pair pair = new Pair(numbers.get(i), numbers.get(j));
                if (closest.compareTo(pair) > 0) {
                    closest = pair;
                }
            }
        }
        return closest;
    }

    private static RichNumber build(int index, int value) {
        return new RichNumber(index, value);
    }

    private static Integer ofWeight(Integer num) {
        int temp = num;
        int sum = 0;
        while (temp > 0) {
            sum += temp % 10;
            temp = temp / 10;
        }
        return sum;
    }

    private static class Pair implements Comparable<Pair> {
        private RichNumber first;

        private RichNumber second;

        public Pair(RichNumber first, RichNumber second) {
            if (first.weight > second.weight) {
                this.first = second;
                this.second = first;
            } else {
                this.first = first;
                this.second = second;
            }
        }

        public int diff() {
            return second.weight - first.weight;
        }

        public int weight() {
            return first.weight + second.weight;
        }

        public int[][] toArray() {
            int[][] ints = new int[2][3];
            ints[0] = first.toArray();
            ints[1] = second.toArray();
            return ints;
        }

        @Override
        public int compareTo(Pair o) {
            if (diff() < o.diff()) {
                return -1;
            } else if (diff() > o.diff()) {
                return 1;
            }
            if (diff() == o.diff()) {
                if (weight() < o.weight()) {
                    return -1;
                } else if (weight() > o.weight()) {
                    return 1;
                }
            }
            return first.index - o.first.index;
        }
    }

    private static class RichNumber {

        private int index;

        private int weight;

        private int value;

        public RichNumber(int index, int value) {
            this.index = index;
            this.value = value;
            this.weight = ofWeight(value);
        }

        public int[] toArray() {
            int[] arr = new int[3];
            arr[0] = weight;
            arr[1] = index;
            arr[2] = value;
            return arr;
        }
    }

}
