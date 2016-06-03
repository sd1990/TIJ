package org.songdan.tij.generics;

import java.util.*;

public class Generators {
    public static <T> Collection<T> fill(Collection<T> coll,Generator<T> gen,int n){
        for (int i = 0; i < n; i++) {
            coll.add(gen.next());
        }
        return coll;
    }
    
    public static <T> List<T> fill(List<T> list,Generator<T> gen,int n
           ){
        for (int i = 0 ; i < n; i++) {
            list.add(gen.next());
        }
        return list;
    }
    public static <T> Set<T> fill(Set<T> list,Generator<T> gen,int n
            ){
        for (int i = 0 ; i < n; i++) {
            list.add(gen.next());
        }
        return list;
    }
    public static <T> Queue<T> fill(Queue<T> list,Generator<T> gen,int n
            ){
        for (int i = 0 ; i < n; i++) {
            list.add(gen.next());
        }
        return list;
    }
    
    public static void main(String[] args) {
        /*
         * 对于linkedList来说，是无法根据参数类型来完成的，必须显示强传
         */
        fill((Queue)new LinkedList<Integer>(),new Fibonacci(),5);
        fill(new ArrayList<Integer>(), new Fibonacci(), 10);
    }
}
