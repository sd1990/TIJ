package org.songdan.tij.generics;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 不带泛型的Stack
 *
 * @author Songdan
 * @date 2016/4/26
 */
public class StackNoGeneric {

    private Object[] elements;

    private int size = 0;

    private static final int DEFAULT_INITAL_CAPACITY = 16;

    public StackNoGeneric() {
        elements = new Object[DEFAULT_INITAL_CAPACITY];
    }

    public void push(Object o) {
        /*
        确保数组大小
         */
        ensureCapacity();
        elements[size++] = o;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object o = elements[--size];
        elements[size] = null;
        return o;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}
