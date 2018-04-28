package org.songdan.tij.algorithm;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 算法集合
 * 
 * @author Songdan
 * @date 2016/8/9
 */
public class Algorithms {

    /**
     * 数组算法
     */
    static class ArrayAlgorithms {

        /**
         * 冒泡排序
         * 
         * @param arr
         */
        public static void bubbleSort(int[] arr) {
            // 外层控制遍历次数
            for (int i = 0; i < arr.length - 1; i++) {
                // 内存控制每次遍历的比较次数,每次都是从第一个开始，
                for (int j = 0; j < arr.length - 1 - i; j++) {
                    if (arr[j] > arr[j + 1]) {
                        swap(arr, j, j + 1);
                    }
                }
            }
        }

        /**
         * 选择算法
         * 
         * @param arr
         */
        public static void selectSort(int[] arr) {
            // 外层控制每次遍历的元素，最先确定第一个元素，第二次从第二个元素开始比较
            for (int i = 0; i < arr.length - 1; i++) {
                // 内层控制每次遍历比较的元素
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[i] > arr[j]) {
                        swap(arr, i, j);
                    }
                }
            }
        }

        /**
         * 快速排序，分治算法
         * 
         * @param arr
         */
        public static void quickSort(int[] arr) {
            int left = 0;
            int right = arr.length - 1;
            quickSortInner(arr, left, right);
        }

        /**
         * 快速排序，分治算法
         *
         * @param arr
         */
        public static void quickSortV2(int[] arr) {
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            int left = 0;
            int right = arr.length - 1;
            QuickSortAction quickSortAction = new QuickSortAction(arr, left, right);
            forkJoinPool.execute(quickSortAction);
            while (!quickSortAction.isDone()) {

            }
        }

        private static void quickSortInner(int[] arr, int left, int right) {
            if (left >= right) {
                return;
            }
            int i = left;
            int j = right;
            while (i != j) {
                for (; i < j && arr[i] <= arr[left]; i++) {

                }
                for (; i < j && arr[j] > arr[left]; j--) {

                }
                if (i < j) {
                    swap(arr, j, i);
                }
            }
            swap(arr, left, i);
            quickSortInner(arr, left, i - 1);
            quickSortInner(arr, i + 1, right);
        }

        static class QuickSortAction extends RecursiveAction{

            private int[] arr;

            private int left;

            private int right;

            public QuickSortAction(int[] arr, int left, int right) {
                this.arr = arr;
                this.left = left;
                this.right = right;
            }

            @Override
            protected void compute() {
                if (left >= right) {
                    return;
                }
                int i = left;
                int j = right;
                while (i != j) {
                    for (; i < j && arr[i] < arr[left]; i++) {

                    }
                    for (; i < j && arr[j] > arr[left]; j--) {

                    }
                    if (i < j) {
                        swap(arr, j, i);
                    }
                }
                System.out.println(i);
                swap(arr, left, i);
                QuickSortAction leftSort = new QuickSortAction(arr, left, i - 1);
                QuickSortAction rightSort = new QuickSortAction(arr, i + 1, right);
                invokeAll(leftSort,rightSort);
            }
        }

        /**
         * 桶排序，需要确定数据的范围，浪费空间，数据要求必须为大于等于0的正整数
         * @param arr
         */
        public static void bucketSort(int[] arr,int range) {
            int[] book = new int[range];
            for (int i : arr) {
                book[i]++;
            }
            for (int i = 0,j=0; i < book.length; i++) {
                int count = book[i];
                if (count !=0) {
                    for (int k = 0; k < count; k++) {
                        arr[j] = i;
                        j++;
                    }
                }
            }
        }

        private static void swap(int[] arr, int j, int i) {
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }

        public static String toString(int[] arr) {
            StringBuilder builder = new StringBuilder("[");
            for (int i = 0; i < arr.length; i++) {

                if (i == arr.length - 1) {
                    builder.append(arr[i]).append("]");
                } else {
                    builder.append(arr[i]).append(",");
            }
            }
            return builder.toString();
        }

        private static void fillArray(int[] arr) {
            Random random = new Random();
            for (int i = 0; i < arr.length; i++) {
                arr[i] = random.nextInt(arr.length*2);
            }
        }

        public static void main(String[] args) throws InterruptedException {
            int length = 100;
            int[] arr = new int[length];
            fillArray(arr);
//            arr = new int[]{4,4,4,4};
            System.out.println(toString(arr));
            int[] copy = new int[arr.length];
            System.arraycopy(arr,0,copy,0, arr.length);
            long startTime = System.currentTimeMillis();
            quickSort(arr);
            System.out.println("v1 : "+(System.currentTimeMillis()-startTime));
            System.out.println(toString(arr));
//            startTime = System.currentTimeMillis();
//            quickSortV2(copy);
//            System.out.println("v2 : " + (System.currentTimeMillis() - startTime));
//            System.out.println(toString(copy));
        }
    }

    /**
     * 字符串算法
     */
    static class StringAlgorithms {

        /**
         * 字符串反转
         */
        public static String reverse(String target) {
            // StringBuilder builder = new StringBuilder(target);
            StringBuilder builder = new StringBuilder();
            String[] strs = target.split("\\s+");
            for (int i = 0; i < strs.length; i++) {
                builder.append(strs[strs.length - i - 1]).append(" ");
            }
            return builder.toString().trim();
        }

        /**
         * 字符串反转，完整的反转，利用栈结构
         * 
         * @param target
         * @return
         */
        public static String reverse2(String target) {
            boolean isBlank = false;
            StringBuilder build = new StringBuilder();
            char[] chars = target.toCharArray();
            LinkedList<String> list = new LinkedList<>();
            for (int i = 0; i < chars.length; i++) {

                char ch = chars[i];
                if (" ".equals(String.valueOf(ch))) {
                    if (!isBlank) {
                        list.add(build.toString());
                        build = new StringBuilder();
                    }
                    isBlank = true;
                } else {
                    if (isBlank) {
                        list.add(build.toString());
                        build = new StringBuilder();
                    }
                    isBlank = false;
                }
                build.append(ch);
            }
            list.add(build.toString());
            String str = null;
            StringBuilder builder = new StringBuilder();
            while ((str = list.pollLast()) != null) {
                builder.append(str);
            }
            return builder.toString();
        }

    }

    /**
     * 缓存算法
     */
    static class CacheAlgorithms {

        /**
         * LRU(Least Recently Used)算法，最近最久未使用，移除时间最久远的元素
         */
        static class LRUCache<K, V> {

            private LinkedList<K> list = new LinkedList<>();

            private ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();

            private int size;

            public LRUCache(int size) {
                this.size = size;
            }

            public V get(K key) {
                if (!cache.containsKey(key)) {
                    return null;
                }
                useLRUKey(key);
                return cache.get(key);
            }

            public void put(K key, V value) {
                V v = cache.get(key);
                if (v != null) {
                    cache.replace(key, v, value);
                } else {
                    if (isFull()) {
                        cache.remove(getLRUKey());
                    }
                    cache.putIfAbsent(key, value);
                }
                useLRUKey(key);
            }

            private K getLRUKey() {
                return list.removeLast();
            }

            private boolean isFull() {
                return list.size() >= size;
            }

            /**
             * 使用LRUkey
             * 
             * @param key
             */
            private synchronized void useLRUKey(K key) {
                // 将这个key对应的元素移动到链表的头部
                list.remove(key);
                list.addFirst(key);
            }

            @Override
            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                for (K key : list) {
                    stringBuilder.append(key + ":" + cache.get(key)).append(" ");
                }
                return stringBuilder.toString();
            }
        }

        static class LFUCache<K, V> {

            private ConcurrentHashMap<K, V> cache = new ConcurrentHashMap<>();

            private class CountEntity implements Comparable<CountEntity> {

                private K key;

                private AtomicInteger count;

                public AtomicInteger getCount() {
                    return count;
                }

                public K getKey() {
                    return key;
                }

                public CountEntity(K key) {
                    this.count = new AtomicInteger(1);
                    this.key = key;
                }

                @Override
                public boolean equals(Object o) {
                    if (this == o) return true;
                    if (o == null || getClass() != o.getClass()) return false;

                    CountEntity that = (CountEntity) o;

                    if (key != null ? !key.equals(that.key) : that.key != null) return false;

                    return true;
                }

                @Override
                public int hashCode() {
                    int result = key != null ? key.hashCode() : 0;
                    result = 31 * result;
                    return result;
                }

                @Override
                public String toString() {
                    return "CountEntity{" +
                            "key=" + key +
                            ",count=" + count +
                            '}';
                }

                @Override
                public int compareTo(CountEntity other) {
                    if (other==null) {
                        return -1;
                    }
                    return count.get() >= other.count.get() ? -1 : 1;
                }
            }

            private Object[] count;

            private int size;

            public LFUCache(int size) {
                this.size = size;
                count = new Object[size];
            }

            public V get(K key) {
                if (!cache.containsKey(key)) {
                    return null;
                }
                useLFUKey(key);
                return cache.get(key);
            }

            public void put(K key, V value) {
                V v = cache.get(key);
                if (v != null) {
                    cache.replace(key, v, value);
                } else {
                    if (isFull()) {
                        cache.remove(getLFUKey());
                    }
                    cache.putIfAbsent(key, value);
                }
                useLFUKey(key);
            }

            private void useLFUKey(K key) {
                Object[] countEntities=  getNotNullArray();
                CountEntity countEntity = new CountEntity(key);
                int index = 0;
                if ((index = indexOf(countEntities,countEntity))!=-1) {
                    ((CountEntity)countEntities[index]).getCount().incrementAndGet();
                }else {
                    Object[] newCountEntities = new Object[countEntities.length + 1];
                    System.arraycopy(countEntities,0,newCountEntities,0,countEntities.length);
                    newCountEntities[countEntities.length] = countEntity;
                    countEntities = newCountEntities;
                }
                Arrays.sort(countEntities);
                count = new Object[size];
                System.arraycopy(countEntities,0,count,0,countEntities.length);
            }

            private int indexOf(Object[] countEntities, CountEntity countEntity) {
                for (int i = 0; i < countEntities.length; i++) {
                    CountEntity _countEntity = (CountEntity) countEntities[i];
                    if (countEntity.equals(_countEntity)) {
                        return i;
                    }
                }
                return -1;
            }

            private boolean contains(Object[] countEntities, CountEntity countEntity) {
                for (Object entity : countEntities) {
                    CountEntity _countEntity = (CountEntity) entity;
                    if (countEntity.equals(_countEntity)) {
                        return true;
                    }
                }
                return false;
            }

            private K getLFUKey() {
                CountEntity countEntity = (CountEntity) count[size - 1];
                count[size-1] = null;
                return countEntity.getKey();
            }

            private boolean isFull() {
                return size <= getNotNullArray().length;
            }

            private Object[] getNotNullArray() {
                List<Object> list = new ArrayList<>();
                for (Object o : count) {
                    if (o != null) {
                        list.add(o);
                    }
                }
                return list.toArray();
            }

            @Override
            public String toString() {
                return "LFUCache{" +
                        "cache=" + cache +
                        ", count=" + Arrays.toString(count) +
                        ", size=" + size +
                        '}';
            }
        }

    }

    public static void main(String[] args) {
        String reverse = StringAlgorithms.reverse("i   am  songdan");
        System.out.println(reverse);
        String reverse2 = StringAlgorithms.reverse2("i   am  songdan");
        System.out.println(reverse2);
        CacheAlgorithms.LFUCache<String, Integer> cache = new CacheAlgorithms.LFUCache<>(3);
        cache.put("2", 1);
        cache.put("1", 1);
//        cache.put("2", 1);
        cache.put("1", 1);
        cache.put("4", 1);
//        cache.put("3", 1);
//        cache.put("2", 2);
//        cache.put("2", 2);
        cache.put("4", 2);
        cache.put("3", 2);
//        cache.put("3", 2);
        System.out.println(cache);
    }

}
