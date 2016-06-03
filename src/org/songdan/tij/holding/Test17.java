package org.songdan.tij.holding;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test17 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        Map<String,String> map = new HashMap<>();
        map.put("a", "123");
        map.put("b", "234");
        map.put("c", "345");
        map.put("d", "456");
        long addEnd = System.nanoTime();
        System.out.println(addEnd-start);
        System.out.println(map);
        System.out.println(System.nanoTime()-addEnd);
        
        long start2 = System.nanoTime();
        Map<String,String> linkMap = new LinkedHashMap<>();
        linkMap.put("a", "123");
        linkMap.put("b", "234");
        linkMap.put("c", "345");
        linkMap.put("d", "456");
        long addEnd2 = System.nanoTime();
        System.out.println(addEnd2-start2);
        System.out.println(linkMap);
        System.out.println(System.nanoTime()-addEnd2);
    }
}
