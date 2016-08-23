package org.songdan.tij.algorithm;

import java.util.LinkedList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 算法集合
 * @author Songdan
 * @date 2016/8/9
 */
public class Algorithms {

    /**
     * 数组算法
     */
    static class ArrayAlgorithms{

        /**
         * 冒泡排序
         * @param arr
         */
        public static void bubbleSort(int[] arr) {
            //外层控制遍历次数
            for (int i = 0; i < arr.length-1; i++) {
                //内存控制每次遍历的比较次数,每次都是从第一个开始，
                for (int j = 0; j < arr.length-1-i; j++) {
                    if (arr[j]>arr[j+1]) {
                        swap(arr, j, j + 1);
                    }
                }
            }
        }

        /**
         * 选择算法
         * @param arr
         */
        public static void selectSort(int[] arr) {
            //外层控制每次遍历的元素，最先确定第一个元素，第二次从第二个元素开始比较
            for (int i = 0; i < arr.length-1; i++) {
                //内层控制每次遍历比较的元素
                for (int j = i+1; j <arr.length ; j++) {
                    if (arr[i] > arr[j]) {
                        swap(arr,i,j);
                    }
                }
            }
        }

        /**
         * 快速排序，分治算法
         * @param arr
         */
        public static void quickSort(int[] arr) {
            int left = 0;
            int right = arr.length - 1;
            quickSortInner(arr, left, right);
        }

        private static void quickSortInner(int[] arr, int left, int right) {
            if (left >= right) {
                return ;
            }
            int i = left;
            int j = right;
            while (i != j) {
                for (; i < j && arr[j] >= arr[left]; j--) {

                }
                for (; i < j && arr[i] <= arr[left]; i++) {

                }
                if (i < j) {
                    swap(arr, j, i);
                }
            }
            swap(arr,left,i);
            quickSortInner(arr, left, i - 1);
            quickSortInner(arr,i+1,right);
        }

        private static void swap(int[] arr, int j, int i) {
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }

        public static String toString(int[] arr) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {

                if (i == arr.length -1) {
                    builder.append(arr[i]);
                }else {
                    builder.append(arr[i]).append(",");
                }
            }
            return builder.toString();
        }

        private static void fillArray(int[] arr) {
            Random random = new Random();
            for (int i = 0; i < arr.length; i++) {
                arr[i]=random.nextInt(100);
            }
        }

        public static void main(String[] args) {
            int[] arr = new int[10];
            fillArray(arr);
            System.out.println(toString(arr));
            long startTime = System.nanoTime();
//            bubbleSort(arr);
//            selectSort(arr);
            quickSort(arr);
            System.out.println("time : "+(System.nanoTime()-startTime));
            System.out.println(toString(arr));
        }
    }

    /**
     * 字符串算法
     */
    static class StringAlgorithms{

        /**
         * 字符串反转
         */
       public static String reverse(String target) {
//           StringBuilder builder = new StringBuilder(target);
           StringBuilder builder = new StringBuilder();
           String[] strs = target.split("\\s+");
           for (int i = 0; i < strs.length; i++) {
               builder.append(strs[strs.length - i - 1]).append(" ");
           }
           return builder.toString().trim();
       }

        /**
         * 字符串反转，完整的反转，利用栈结构
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
               if(" ".equals(String.valueOf(ch))){
                   if(!isBlank) {
                       list.add(build.toString());
                       build = new StringBuilder();
                   }
                   isBlank = true;
               }else {
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
           while ((str=list.pollLast())!=null) {
               builder.append(str);
           }
           return builder.toString();
       }

    }

    /**
     * 缓存算法
     */
    static class CacheAlgorithms{

        /**
         * LRU算法，最近最少被使用算法，移除时间最久远的元素
         */
        static class LRUCache<K,V>{

            private LinkedList<K> list = new LinkedList<>();

            private Map<K, V> cache = new ConcurrentHashMap<>();
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
                if (v!=null) {
                    cache.replace(key,v,value);
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
                return cache.size() >= size;
            }

            /**
             * 使用LRUkey
             * @param key
             */
            private synchronized void useLRUKey(K key) {
                //将这个key对应的元素移动到链表的头部
                list.remove(key);
                list.addFirst(key);
            }

            @Override
            public String toString() {
                StringBuilder stringBuilder = new StringBuilder();
                for (K key:list) {
                    stringBuilder.append(key + ":" + cache.get(key)).append(" ");
                }
                return stringBuilder.toString();
            }
        }

    }

    public static void main(String[] args) {
        String reverse = StringAlgorithms.reverse("i   am  songdan");
        System.out.println(reverse);
        String reverse2 = StringAlgorithms.reverse2("i   am  songdan");
        System.out.println(reverse2);
        CacheAlgorithms.LRUCache<String, Integer> cache = new CacheAlgorithms.LRUCache<>(3);
        cache.put("2",1);
        cache.put("1",1);
        cache.put("2",1);
        cache.put("1",1);
        cache.put("4",1);
        cache.put("3",1);
        System.out.println(cache);
    }

}
