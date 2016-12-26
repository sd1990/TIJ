package org.songdan.tij.generics;

class Generic<T> {

}

public class ArrayOfGeneric {

    static final int SIZE = 100;

    static Generic<Integer>[] gia;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        gia = (Generic<Integer>[]) new Object[SIZE];
        System.out.println(gia.getClass().getSimpleName());
    }
}
