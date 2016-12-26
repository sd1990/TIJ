package org.songdan.tij.mymap;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {

    public static void main(String[] args) {
        HashMap<String, String> map = new MyHashMap<>(new TreeMap<String, String>());
        map.put("1000", "0");
        map.put("10001001", "0-1");
        map.put("1001", "1");
        map.put("10011001", "1-1");
        map.put("10011002", "1-2");
        map.put("10011003", "1-3");
        map.put("1002", "2");
        map.put("10021002", "2-2");
        map.put("10021001", "2-1");
        map.put("10021003", "2-3");
        map.put("1003", "3");
        map.put("10031002", "3-2");
        map.put("10031001", "3-1");
        map.put("10031003", "3-3");
        System.out.println(map);
        Map<String, String> _map = map;
        System.out.println(_map);
    }
}
