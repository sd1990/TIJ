package org.songdan.tij.array.holding;

import java.util.*;

public class AddingGroups {
    public static void main(String[] args) {
        /*elementData = c.toArray();
        size = elementData.length;
        // c.toArray might (incorrectly) not return Object[] (see 6260652)
        if (elementData.getClass() != Object[].class)
            elementData = Arrays.copyOf(elementData, size, Object[].class);*/
        Collection<Integer> collection = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
        /*boolean result = false;
        for (T element : elements)
            result |= c.add(element);*/
        Collections.addAll(collection, 11,12,13,14,15);
        /*Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size + numNew);  // Increments modCount
        System.arraycopy(a, 0, elementData, size, numNew);
        size += numNew;
        return numNew != 0;*/
        collection.addAll(collection);
        System.out.println(collection);
        List<Integer> list = Arrays.asList(16,17,18,19);
        list.add(20);
    }
}
