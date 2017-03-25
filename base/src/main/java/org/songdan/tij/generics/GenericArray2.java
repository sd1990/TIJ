package org.songdan.tij.generics;

public class GenericArray2<T> {

    private Object[] array;

    public GenericArray2(int size) {
        array = new Object[size];
    }

    public void set(int index, T item) {
        array[index] = item;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        return (T) array[index];
    }

    @SuppressWarnings("unchecked")
    public T[] rep() {
        return (T[]) array;
    }

    public static void main(String[] args) {
        GenericArray2<Integer> gia = new GenericArray2<>(10);
        for (int i = 0; i < 10; i++) {
            gia.set(i, i);
        }
        int i = 0;
        Integer in = null;
        try {
            while ((in = gia.get(i)) != null) {
                System.out.println(in);
                i++;
            }
        }
        catch (Exception e) {

        }
        Integer[] rep = gia.rep();
    }
}
