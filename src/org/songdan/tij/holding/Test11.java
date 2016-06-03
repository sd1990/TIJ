package org.songdan.tij.holding;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Test11 {

    public static void main(String[] args) {
        IteratorDemo demo = new IteratorDemo();
        Set<Gerbil> set = new HashSet<>();
        set.add(new Gerbil(1));
        set.add(new Gerbil(2));
        set.add(new Gerbil(3));
        set.add(new Gerbil(4));
        demo.iterator(set.iterator());
    }
}

class IteratorDemo {
    public void iterator(Iterator iterator){
        while (iterator.hasNext()) {
            /*
             * 即使向上转型了，多态依然有效
             */
            Object object = (Object) iterator.next();
            System.out.println(object);
        }
    }
}
