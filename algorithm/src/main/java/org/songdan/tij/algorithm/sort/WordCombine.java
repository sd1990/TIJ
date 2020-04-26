package org.songdan.tij.algorithm.sort;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Songdan
 * @create: 2019-11-23 20:46
 **/
public class WordCombine {

    public static void main(String[] args) {
        String[] arr = {"afternight","quick","time","quicktime","after","night","afternightmoon"};
        System.out.println(findCombine(arr));
    }

    private static List<CombineStr> findCombine(String[] strArr) {
        List<CombineStr> list = Lists.newArrayList();
        Arrays.sort(strArr);
        for (int i = 0; i < strArr.length; i++) {
            String s1 = strArr[i];
            //在剩余元素里面找到
            for (int j = i+1; j < strArr.length; j++) {
                String s2 = strArr[j];
                if (s2.length()>s1.length()) {
                    String prefix = s2.substring(0, s1.length());
                    if (s1.compareTo(prefix) < 0) {
                        break;
                    }
                    if (s1.compareTo(prefix) > 0) {
                        continue;
                    }
                    String s3 = s2.substring(s1.length());
                    int s3Index = Arrays.binarySearch(strArr, s3);
                    if (s3Index > 0 && s3Index != i) {
                        list.add(new CombineStr(s2, Pair.of(s1, s3)));
                    }
                }
            }
        }
        return list;
    }

    private static class CombineStr{
        private String value;

        private Pair<String, String> pair;

        public CombineStr(String value, Pair<String, String> pair) {
            this.value = value;
            this.pair = pair;
        }

        @Override
        public String toString() {
            return "CombineStr{" +
                    "value='" + value + '\'' +
                    ", pair=" + pair +
                    '}';
        }
    }

}
