package org.songdan.tij.holding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 演示Map特性的示例
 * Created by SongDan on 2017/3/12.
 */
public class MapDemo {

    public static void main(String[] args) {
//        testOrder();
        HashMap<Object, Object> map = new HashMap<>(2);
        map.put(10241234, 1);
        map.put(2, 2);
        int h = Integer.valueOf(1024123456).hashCode();
        System.out.println(h>>>16);
        System.out.println(h ^ (h >>> 16));
        Integer k = 32768;
        h ^= k.hashCode();

        //二次散列
        h ^= (h >>> 20) ^ (h >>> 12);
        System.out.println(h ^ (h >>> 7) ^ (h >>> 4));

    }

    /**
     * 用来测试HashMap和LinkedHashMap的元素顺序
     */
    public static void testOrder() {
        Person xiaoming = new Person();
        xiaoming.setName("xiaoming");
        xiaoming.setAge(20);
        Person xiaohong = new Person();
        xiaohong.setName("xiaohong");
        xiaohong.setAge(18);
        Person xiaoli = new Person();
        xiaoli.setName("xiaoli");
        xiaoli.setAge(25);
        Map<String, Person> hashMap = new HashMap<>();
        Map<String, Person> insertLinkedMap = new LinkedHashMap<>();
        Map<String, Person> visitLinkedMap = new LinkedHashMap<>(16,0.75f,true);
        hashMap.put(xiaoming.getName(), xiaoming);
        insertLinkedMap.put(xiaoming.getName(), xiaoming);
        visitLinkedMap.put(xiaoming.getName(), xiaoming);
        hashMap.put(xiaohong.getName(), xiaohong);
        insertLinkedMap.put(xiaohong.getName(), xiaohong);
        visitLinkedMap.put(xiaohong.getName(), xiaohong);
        hashMap.put(xiaoli.getName(), xiaoli);
        insertLinkedMap.put(xiaoli.getName(), xiaoli);
        visitLinkedMap.put(xiaoli.getName(), xiaoli);
        //=================测试LinkedHashMap构造函数中传入顺序控制参数的不同，重新插入对插入顺序不产生影响===========================
//        insertLinkedMap.put(xiaoming.getName(), xiaoming);
//        visitLinkedMap.put(xiaoming.getName(), xiaoming);
        //============================================
        //============================================
        insertLinkedMap.get(xiaoming.getName());
        visitLinkedMap.get(xiaoming.getName());
        //============================================


        printMap(hashMap);
        printMap(insertLinkedMap);
        printMap(visitLinkedMap);
    }

    private static void printMap(Map<String, Person> map) {
        System.out.println(map.getClass());
        Iterator<Map.Entry<String, Person>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Person> next = iterator.next();
            System.out.println(next.getKey() + ":" + next.getValue().getAge());
        }

    }

}
