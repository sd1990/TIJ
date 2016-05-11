package org.songdan.tij.array.generics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 比较原生类型，无限制通配符，Object的区别
 * Created by PC on 2016/4/26.
 */
public class GenericTest {

    @Test
    public void testPrimitive() {
        /*
        原生类型逃避了类型检查，失去了类型安全性
        instanceOf 操作 使用原生类型
         */
        List list = new ArrayList<>();
        list.add("hello world");
        list.add(123);
        for (Object o : list) {
            System.out.println(o);
        }
        System.out.println(list instanceof List);
    }

    @Test
    public void testObject() {
        /*
        提供了类型安全性,不可协变
         */
        List<Object> list = new ArrayList<>();
//        list = new ArrayList<String>();
        list.add("hello world");
        list.add(123);
        for (Object o : list) {
            System.out.println(o);
        }
    }

    @Test
    public void testUnLimited() {
        /*
        表示只能包含某种未知对象类型的集合
        不能添加元素(除了null)，获取元素通过Objec获取，更灵活的使用参照有限制的泛型通配符
         */
        List<?> list = new ArrayList<>();
        list = new ArrayList<String>();
        list = new ArrayList<Integer>();
//        list.add("hello world");
//        list.add(123);
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void swap(List<?> list, int i, int j) {
        //        list.set(i, list.set(j, list.get(i)));
        swapHelper(list, i, j);
    }

    private static <E> void swapHelper(List<E> list, int i, int j) {
        list.set(i, list.set(j, list.get(i)));
    }

}
