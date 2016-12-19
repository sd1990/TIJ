package org.songdan.tij.random;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomDemo {

    public static void main(String[] args) {

        Random random = new Random();

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            int nextInt = random.nextInt(100);
            if (nextInt < 50) {
                System.out.println("参与奖");
                fun(map, 0);
            }
            else if (nextInt >= 50 && nextInt < 80) {
                System.out.println("三等奖");
                fun(map, 3);
            }
            else if (nextInt >= 80 && nextInt < 95) {
                System.out.println("二等奖");
                fun(map, 2);
            }
            else {
                System.out.println("一等奖");
                fun(map, 1);
            }
        }
        System.out.println(map);
    }

    private static void fun(Map<Integer, Integer> map, Integer integer) {
        Integer num = map.get(integer);
        if (num == null) {
            map.put(integer, 1);
        }
        else {
            System.out.println(num);
            map.put(integer, ++num);
        }
    }
}
