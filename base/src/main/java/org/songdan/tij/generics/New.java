package org.songdan.tij.generics;

import java.util.HashMap;
import java.util.Map;

public class New {

    public static <K, V> Map<K, V> map() {
        return new HashMap<>();
    }

    public static void main(String[] args) {
        Map<Integer, String> map = New.map();
        map.put(1, "a");
        System.out.println(map);
    }
}
