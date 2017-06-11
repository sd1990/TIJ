package org.songdan.tij.holding.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Songdan
 * @date 2017/5/18 17:53
 */
public class ForEachDemo {

    public static void main(String[] args) {
        List<Integer> originList = buildOriginList();

        List<Integer> resultList = new ArrayList<>();

        int count = sequentialCount(originList);
        int parallelCount = parallelCount(originList);
        System.out.println(count == parallelCount);
    }

    private static int parallelCount(List<Integer> originList) {
        List<Integer> countList = new ArrayList<>();
        originList.parallelStream().forEach((num)-> {
            if (num>100) {
                countList.add(num);
            }
        });
        return countList.size();
    }

    private static int sequentialCount(List<Integer> originList) {
        int sum = 0;
        for (Integer integer : originList) {
            if (integer > 100) {
                sum++;
            }
        }
        return sum;
    }

    private static List<Integer> buildOriginList() {
        Random random = new Random();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            result.add(i);
        }
        return result;
    }

}
