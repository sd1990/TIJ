package org.songdan.tij.algorithm.sort;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: Songdan
 * @create: 2019-11-23 21:38
 **/
public class Frequency {

    public static void main(String[] args) {

        String[] arr = {"abc","abc","a","i","am","coco","and","coco","where"};
        sortByFrequency(arr);

    }

    private static void sortByFrequency(String[] arr) {

        Arrays.sort(arr);
        Record[] records = new Record[arr.length];
        String word = arr[0];
        int freq = 1;
        int m = 0;
        for (int i = 1; i < arr.length; i++) {

            if (arr[i].equals(word)) {
                freq++;
            } else {
                //重新初始化
                records[m++] = new Record(word, freq);
                word = arr[i];
                freq = 1;
            }
        }
        records[m++] = new Record(word, freq);
        Arrays.sort(records, 0, m, Comparator.comparingInt(o -> o.freq));
        for (int i = m-1; i >= 0; i--) {
            System.out.println(records[i]);
        }

    }

    private static class Record{
        private String word;

        private int freq;

        public Record(String word, int freq) {
            this.word = word;
            this.freq = freq;
        }

        @Override
        public String toString() {
            return "Record{" +
                    "word='" + word + '\'' +
                    ", freq=" + freq +
                    '}';
        }
    }

}
