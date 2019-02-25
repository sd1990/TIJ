package org.songdan.tij.thread;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ConcurrentHashMap死循环
 *
 * @author: Songdan
 * @create: 2019-02-16 23:06
 **/
public class ConcurrentHashMapBug {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.computeIfAbsent("AaAa", key -> map.computeIfAbsent("BbBb", key2 -> "value"));
    }

}
