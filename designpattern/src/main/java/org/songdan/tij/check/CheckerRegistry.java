package org.songdan.tij.check;

import org.songdan.tij.check.checker.Checker;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: Songdan
 * @create: 2019-05-15 19:40
 **/
public class CheckerRegistry {

    private static ConcurrentHashMap<String, Checker> map = new ConcurrentHashMap<>();

    public static Checker getByName(String name) {
        return map.get(name);
    }

    public static boolean register(String name, Checker checker) {
        return map.putIfAbsent(name, checker) == null;
    }

}
