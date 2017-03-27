package org.songdan.tij.holding;

import java.util.*;

/**
 * 集合添加操作
 */
public class AddingGroups {

    public static void main(String[] args) {
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        //只要collection发生变化了，就返回true，源码采用的是位运算
        Collections.addAll(collection, 11, 12, 13, 14, 15);
        collection.addAll(collection);
        System.out.println(collection);
        List<Integer> list = Arrays.asList(16, 17, 18, 19);
        list.add(20);//Arrays.ArrayList是不可变的列表，此处会抛出异常
    }
}
