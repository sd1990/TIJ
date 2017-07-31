package org.songdan.tij.holding.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Songdan
 * @date 2017/7/14 10:57
 */
public class MapDemo {

    public static void main(String[] args) {
        List<Integer> integers = buildOriginList();
        List<Integer> collect = integers.stream().map(element -> element * 2).collect(Collectors.toList());
        System.out.println(integers);
        System.out.println(collect);
    }

    private static List<Integer> buildOriginList() {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            result.add(i);
        }
        return result;
    }
}
