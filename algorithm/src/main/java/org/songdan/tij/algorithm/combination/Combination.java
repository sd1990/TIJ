package org.songdan.tij.algorithm.combination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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
    public static <R extends Comparable<R>> List<List<R>> allCombination(List<R> arr) {
        int length = arr.size();
        // 组合由多少个元素组成的
        List<List<R>> result = new ArrayList<>();
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
    private static <R extends Comparable<R>> List<List<R>> combinationSelect(List<R> arr, int i) {
        return new DFSCombination<>(arr, i).select();
    }

    public static void main(String[] args) {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        System.out.println(allCombination(integers));
        List<Coupon> couponList = new ArrayList<>();
        couponList.add(new Coupon(1, "减10", 10));
        couponList.add(new Coupon(2, "减10", 20));
        couponList.add(new Coupon(3, "减10", 30));
        List<List<Coupon>> allCombination = allCombination(couponList);
        List<CompositeCoupon> list = new ArrayList<>();
        for (List<Coupon> coupons : allCombination) {
            List<String> collect = coupons.stream().map(coupon -> String.valueOf(coupon.getId())).collect(Collectors.toList());
            Integer sum = coupons.stream().map(Coupon::getAmount).mapToInt(Integer::intValue).sum();
            list.add(new CompositeCoupon(String.join("-", collect),sum));
        }
        list.sort((Comparator.comparingInt(CompositeCoupon::getCount)));
        
        System.out.println(list);


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

        public Coupon() {
        }

        public Coupon(int id, String name, int amount) {
            this.id = id;
            this.name = name;
            this.amount = amount;
        }

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

        @Override
        public String toString() {
            return "[" +
                    "id=" + id +
                    ", amount=" + amount +
                    ']';
        }
    }

    private static class CompositeCoupon{
        private String compositeId;

        private Integer count;

        public String getCompositeId() {
            return compositeId;
        }

        public void setCompositeId(String compositeId) {
            this.compositeId = compositeId;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public CompositeCoupon(String compositeId, Integer count) {
            this.compositeId = compositeId;
            this.count = count;
        }
    }


}
