package org.songdan.tij.array.generics;

import java.util.Iterator;
import java.util.List;

public class GenericMethods {
    public <T> void fun(T t){
        System.out.println(t.getClass().getName());
    }
    
    public <X,Y,Z> void fun(X x,Y y,Z z){
        System.out.println(x.getClass().getName());
        System.out.println(y.getClass().getName());
        System.out.println(z.getClass().getName());
    }
    /**
     * 就近原则
     * @param x
     * @param y
     * @param z
     */
    public <X,Y,Z> void fun(X x,Y y,String z){
        System.out.println(z);
        System.out.println(x.getClass().getName());
        System.out.println(y.getClass().getName());
        System.out.println(z.getClass().getName());
    }

    /**
     * Effective Java 第27条目 泛型方法,此方法使用了递归的类型参数<br/>
     * 获取列表的最大值<br/>
     * @param list
     * @param <E>
     * @return
     */
    public <E extends Comparable<E>> E max(List<E> list) {
        Iterator<E> iterator = list.iterator();
        E result = iterator.next();
        while (iterator.hasNext()) {
            E e = iterator.next();
            if (result.compareTo(e) < 0) {
                result = e;
            }
        }
        return result;
    }

    /**
     * Effective Java 第28条目，有限制的泛型通配符，提供了灵活性，遵循PECS原则
     * @param list
     * @param <E>
     * @return
     */
    public <E extends Comparable<? super E>> E maxWithLimited(List<? extends E> list) {
        Iterator<? extends E> iterator = list.iterator();
        E result = iterator.next();
        while (iterator.hasNext()) {
            E e = iterator.next();
            if (result.compareTo(e) < 0) {
                result = e;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.fun("hello world");
        gm.fun(1);
//        gm.fun("a",'a',true);
        gm.fun("a",'a',"hello world");
    }
}
