package org.songdan.tij.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 门店营业时间
 *
 * @author: Songdan
 * @create: 2021-02-20 20:50
 **/
public class ShopTimeCalculator {

    /**
     * 门店A的营业时间是：[t1 ~ t2]。门店B的营业时间是：[t3 ~ t4]。求两个门店的同时在营业的时间区间 [t5 ~ t6]。备注：t1<t2，t3<t4。
     * @param s1
     * @param s2
     * @return
     */
    public static int[] inner(int[] s1, int[] s2) {
        if (s1[0] < s2[0] && s1[1] > s2[1]) {
            return s2;
        }
        if (s2[0] < s1[0] && s2[1] > s1[1]) {
            return s1;
        }
        if (s1[0] < s2[0] && s1[1] < s2[0]) {
            return null;
        }
        if (s2[0] < s1[0] && s2[1] < s1[0]) {
            return null;
        }
        return s1[0] < s2[0] ? new int[]{s2[0], s1[1]} : new int[]{s1[0], s2[1]};


    }

    /**
     * 门店A的营业时间是：[t1 ~ t2]。门店B的营业时间是：[t3 ~ t4]。求两个门店的同时在营业的时间区间 [t5 ~ t6]。备注：t1<t2，t3<t4。
     * @param s1
     * @param s2
     * @return
     */
    public static int[] inner2(int[] s1, int[] s2) {
        if (s1[0] < s2[0]) {
            if (s1[1]>s2[1]) {
                return s2;
            }
            if (s1[1] < s2[0]) {
                return null;
            }
            return new int[]{s2[0], s1[1]};
        } else {
            if (s2[1]>s1[1]) {
                return s1;
            }
            if (s2[1] < s1[0]) {
                return null;
            }
            return new int[]{s1[0], s2[1]};
        }

    }

    /**
     * 门店A的营业时间是：[t1 ~ t2]。门店B的营业时间是：[t3 ~ t4]。求两个门店的同时在营业的时间区间 [t5 ~ t6]。备注：t1<t2，t3<t4。
     * @param s1
     * @param s2
     * @return
     */
    public static int[] inner3(int[] s1, int[] s2) {
        int[] s = merge(s1, s2);
        if (Math.abs(s[0] - s[1]) == 1) {
            return null;
        }
        if (s[1] < 10) {
            return new int[]{s1[s[1]], s2[s[2] - 10]};
        }
        return new int[]{s2[s[1] - 10], s1[s[2]]};
    }

    private static int[] merge(int[] s1, int[] s2) {
        int len = s1.length + s2.length;
        int[] result = new int[len];
        int a = 0;
        int b = 0;
        int c = 0;
        while (a < s1.length && b < s2.length) {
            if (s1[a] < s2[b]) {
                result[c++] = a++;
            } else {
                result[c++] = 10 + b++;
            }
        }
        while (a < s1.length) {
            result[c++] = 10 + a++;
        }
        while (b < s2.length) {
            result[c++] = 10 + b++;
        }
        return result;
    }

    private static void print(int[] arr) {
        if (arr == null) {
            System.out.println("null");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i : arr) {
            builder.append(i).append(",");
        }
        System.out.println(builder.subSequence(0, builder.length() - 1));

    }

    /**
     * 门店A的营业时间是：[ta1 ~ ta2], [ta3 ~ ta4]。 备注：ta1<ta2，ta2<ta3，ta3<ta4。
     *
     * 门店B的营业时间是：[tb1 ~ tb2], [tb3 ~ tb4]。备注：同上。
     *
     * 求两个门店同时在营业的时间区间。
     * @return
     */
    private static List<ShopTime> inner(List<ShopTime> shop1, List<ShopTime> shop2) {
        List<ShopTime> result = new ArrayList<>();
        if (shop1.size() == 0 || shop2.size() == 0) {
            return result;
        }
        int a = 0, b = 0;
        ShopTime s1 = shop1.get(a);
        ShopTime s2 = shop2.get(b);
        ShopTime shopTime;
        if (s1.compareTo(s2) < 0) {
            shopTime = extract(s1, s2, shop1, shop2);
        } else {
            shopTime = extract(s2, s1, shop2, shop1);
        }
        if (shopTime != null) {
            result.add(shopTime);
        }
        result.addAll(inner(shop1, shop2));
        return result;
    }

    /**
     * 门店A的营业时间是：[ta1 ~ ta2], [ta3 ~ ta4]。 备注：ta1<ta2，ta2<ta3，ta3<ta4。
     *
     * 门店B的营业时间是：[tb1 ~ tb2], [tb3 ~ tb4]。备注：同上。
     *
     * 求两个门店同时在营业的时间区间。
     * @return
     */
    private static List<ShopTime> innerLoop(List<ShopTime> shop1, List<ShopTime> shop2) {
        List<ShopTime> result = new ArrayList<>();
        AtomicInteger a = new AtomicInteger(0), b = new AtomicInteger(0);
        while (a.get() < shop1.size() && b.get() < shop2.size()) {
            ShopTime ta = shop1.get(a.get());
            ShopTime tb = shop2.get(b.get());
            if (ta.compareTo(tb) < 0) {
                extract(result, a, b, ta, tb);
            } else {
                extract(result, b, a, tb, ta);
            }
        }
        return result;
    }

    private static void extract(List<ShopTime> result, AtomicInteger a, AtomicInteger b, ShopTime ta, ShopTime tb) {
        if (ta.endTime <= tb.startTime) {
            a.incrementAndGet();
        } else if (ta.endTime > tb.endTime) {
            b.incrementAndGet();
            result.add(tb);
        } else {
            a.incrementAndGet();
            result.add(new ShopTime(tb.startTime, ta.endTime));
        }
    }

    private static ShopTime extract(ShopTime ta,ShopTime tb,List<ShopTime> shopA,List<ShopTime> shopB) {
        if (ta.endTime <= tb.startTime) {
            shopA.remove(0);
            return null;
        } else if (ta.endTime > tb.endTime) {
            shopB.remove(0);
            return tb;
        } else {
            shopA.remove(0);
            return new ShopTime(tb.startTime, ta.endTime);
        }
    }

    public static void main(String[] args) {
        int[] a1 = {9,12};
        int[] a2 = {14,22};
        print(inner(a1, a2));
        print(inner2(a2, a1));
        print(inner3(a2, a1));
        List<ShopTime> shop1 = new ArrayList<>();
        List<ShopTime> shop2 = new ArrayList<>();
        shop1.add(new ShopTime(9, 12));
        shop2.add(new ShopTime(14, 22));
        print(inner(shop1, shop2));
        int[] a3 = {9,12};
        int[] a4 = {10,22};
        print(inner(a3, a4));
        print(inner2(a4, a3));
        print(inner3(a4, a3));
        List<ShopTime> shop3 = new ArrayList<>();
        List<ShopTime> shop4 = new ArrayList<>();
        shop3.add(new ShopTime(9, 12));
        shop4.add(new ShopTime(10, 22));
        print(inner(shop3, shop4));
        List<ShopTime> shop5 = new ArrayList<>();
        List<ShopTime> shop6 = new ArrayList<>();
        shop5.add(new ShopTime(9, 12));
        shop5.add(new ShopTime(14, 21));
        shop6.add(new ShopTime(7, 9));
        shop6.add(new ShopTime(10, 13));
        shop6.add(new ShopTime(14, 22));
        print(innerLoop(shop5, shop6));
        print(inner(shop5, shop6));
    }

    private static void print(List<ShopTime> inner) {
        if (inner.size() == 0) {
            System.out.println("null");
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (ShopTime shopTime : inner) {
            builder.append(shopTime).append(",");
        }
        System.out.println(builder.subSequence(0, builder.length() - 1));
    }


    static class ShopTime implements Comparable<ShopTime>{

        private int startTime;

        private int endTime;

        public ShopTime(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int getStartTime() {
            return startTime;
        }

        public void setStartTime(int startTime) {
            this.startTime = startTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        @Override
        public int compareTo(ShopTime o) {
            return startTime - o.startTime;
        }

        @Override
        public String toString() {
            return "ShopTime{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    '}';
        }
    }

}
