package org.songdan.tij.array.generics;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Stack With Generic
 * @author Songdan
 * @date 2016/4/26
 */
public class Stack<E> {

    private E[] elements;

    private int size = 0;

    private static final int DEFAULT_INITAL_CAPACITY = 16;

    public Stack() {
        elements = (E[])new Object[DEFAULT_INITAL_CAPACITY];
    }


    public void push(E o) {
        /*
        确保数组大小
         */
        ensureCapacity();
        elements[size++] = o;
    }

    private void ensureCapacity() {
        if (elements.length==size) {
            Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public E pop() {
        if (size==0) {
            throw new EmptyStackException();
        }
        E o = elements[--size];
        elements[size] = null;
        return o;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
