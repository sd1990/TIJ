package org.songdan.tij.algorithm.combination;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    public static List<List<Integer>> allCombination(List<Integer> arr) {
        int length = arr.size();
        // 组合由多少个元素组成的
        List<List<Integer>> result = new ArrayList<>();
        int i = 1;
        while (i <= length) {
            // 生成i个元素的组合
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
    private static List<List<Integer>> combinationSelect(List<Integer> arr, int i) {
        return new DFSCombination<>(arr, i).select();
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        System.out.println(allCombination(integers));
        // Set<String> select = new DFSCombination<>(integers, 1).select();
        // System.out.println(select);
    }

    /**
     * DFS实现组合
     */
    private static class DFSCombination<R extends Comparable<R>> {
        // 标记元素是否已被组合
        private Set<R> bookSet = new HashSet<>();

        private List<R> arr;

        private int n;

        private Map<Integer, R> bucks;

        private List<List<R>> result = new ArrayList<>();

        public DFSCombination(List<R> arr, int n) {
            this.arr = arr;
            this.n = n;
            bucks = new LinkedHashMap<>();
        }

        private void dfs(int index) {
            if (index == n) {
                // 说明组合数满了
                result.add(composite());
                return;
            }

            for (int i = 0; i < arr.size(); i++) {
                R element = arr.get(i);
                if (!bookSet.contains(element)) {
                    if (index > 0) {
                        // 保证一个组合的顺序,从小到大的顺序
                        R lastElement = bucks.get(index - 1);
                        if (lastElement.compareTo(element) > 0) {
                            continue;
                        }
                    }
                    // 第几个位置放置什么元素
                    bucks.put(index, element);
                    bookSet.add(element);
                    dfs(index + 1);
                    bookSet.remove(element);
                }
            }

        }

        public List<List<R>> select() {
            dfs(0);
            return result;
        }

        private List<R> composite() {
            return bucks.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        }

    }

    /**
     * 优惠券
     */
    private static class Coupon implements Comparable<Coupon> {

        private int id;

        private String name;

        private int amount;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public int compareTo(Coupon o) {
            return id - o.id;
        }
    }

}
