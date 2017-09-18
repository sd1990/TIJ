package org.songdan.tij.holding.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Songdan
 * @date 2017/5/18 17:53
 */
public class ForEachDemo {

    public static void main(String[] args) {
        List<Integer> originList = buildOriginList();
        long start = System.currentTimeMillis();
        long count = sequentialCount(originList);
        System.out.println("sequence cost "+(System.currentTimeMillis()-start)+",count :"+count);
        long start2 = System.currentTimeMillis();
        long parallelCount = parallelCount(originList);
        System.out.println("parallel cost "+(System.currentTimeMillis()-start2)+",count :"+parallelCount);
    }

    private static long sequentialCount(List<Integer> originList) {
        return originList.stream().filter(num-> num > 100).count();
    }

    private static long parallelCount(List<Integer> originList) {
        return originList.parallelStream().filter(num-> num > 100).count();
    }

    private static List<Integer> buildOriginList() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            result.add(i);
        }
        return result;
    }

}
