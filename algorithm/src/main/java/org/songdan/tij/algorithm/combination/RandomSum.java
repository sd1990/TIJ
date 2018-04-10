package org.songdan.tij.algorithm.combination;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 10 四月 2018
 */
public class RandomSum {

    private List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) {
        RandomSum randomSum = new RandomSum();
        int[] arr = { 1, 2, 7, 4, 5, 3 };
        randomSum.combination(arr, 3, 8, new ArrayList<>());
        System.out.println(randomSum.result);
        System.out.println(randomSum.new DFS(arr, 3, 8).result);
    }

    public void combination(int[] arr, int n, int m, List<Integer> combination) {
        if (n == 1) {
            Integer choose = choose(arr, m);
            if (choose == null) {
                return;
            }
            combination.add(choose);
            result.add(new ArrayList<>(combination));
            combination.remove(combination.size() - 1);
            return;
        }
        for (int j = 0; j < arr.length - n + 1; j++) {
            int i = arr[j];
            combination.add(i);
            int[] nextArr = new int[arr.length - j - 1];
            System.arraycopy(arr, j + 1, nextArr, 0, arr.length - j - 1);
            combination(nextArr, n - 1, m - i, combination);
            combination.remove(combination.size() - 1);
        }

    }

    private Integer choose(int[] arr, int m) {
        for (int i : arr) {
            if (i == m) {
                return i;
            }
        }
        return null;
    }

    private class DFS {

        private List<List<Integer>> result = new ArrayList<>();

        private LinkedHashSet<Integer> books = new LinkedHashSet<>();

        private int[] arr;

        private int depth;

        private LinkedHashMap<Integer, Integer> bucks = new LinkedHashMap<>();

        public DFS(int[] arr, int depth, int m) {
            this.arr = arr;
            this.depth = depth;
            dfs(1, m);
        }

        public void dfs(int step, int m) {
            if (step == this.depth) {
                Integer choose = choose(arr, m);
                if (choose == null) {
                    return;
                }
                if (books.contains(choose)) {
                    return;
                }
                bucks.put(step, choose);
                result.add(new ArrayList<>(bucks.values()));
                return;
            }
            for (int anArr : arr) {
                if (books.contains(anArr)) {
                    continue;
                }
                books.add(anArr);
                bucks.put(step, anArr);
                dfs(step + 1, m - anArr);
                books.remove(anArr);
            }

        }

    }

}
