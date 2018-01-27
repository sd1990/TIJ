package org.songdan.tij.algorithm.combination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 27 一月 2018
 */
public class Combination {

    /**
     * 所有的组合
     * 
     * @param arr
     * @return
     */
    public static List<String> allCombination(int[] arr) {
        int length = arr.length;
        // 组合由多少个元素组成的
        List<String> result = new ArrayList<>();
        int i = 1;
        while (i <= length) {
            // 生成i类组合
            result.addAll(combinationSelect(arr, i));
            i++;
        }
        return result;
    }

    /**
     * 由n个元素组成的组合
     * 
     * @param arr 数组
     * @param i 组合的元素个数
     * @return 组合集合
     */
    private static List<String> combinationSelect(int[] arr, int i) {
        return new ArrayList<>(new DFSCombination(arr, i).select());
    }

    public static void main(String[] args) {

        System.out.println(allCombination(new int[] { 1, 2, 3, 4, 5 }));
    }

    /**
     * DFS实现组合
     */
    private static class DFSCombination {

        private Set<Integer> bookSet = new HashSet<>();

        private int[] arr;

        private int n;

        private int[] bucks;

        private Set<String> result = new LinkedHashSet<>();

        public DFSCombination(int[] arr, int n) {
            this.arr = arr;
            this.n = n;
            bucks = new int[n];
        }

        private void dfs(int step) {
            if (step == n) {
                // 说明组合数满了
                result.add(arr2Str());
                return;
            }

            for (int i = 0; i < arr.length; i++) {
                int num = arr[i];
                if (!bookSet.contains(num)) {
                    if (step > 0) {
                        // 保证一个组合的顺序,从小到大的顺序
                        if (bucks[step - 1] > num) {
                            continue;
                        }
                    }
                    bucks[step] = num;
                    bookSet.add(num);
                    dfs(step + 1);
                    bookSet.remove(num);
                }
            }

        }

        public Set<String> select() {
            dfs(0);
            return result;
        }

        private String arr2Str() {
            StringBuilder builder = new StringBuilder();
            for (Integer num : bucks) {
                builder.append(num).append("-");
            }
            return builder.substring(0, builder.length() - 1);
        }

    }

}
