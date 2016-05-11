package org.songdan.tij.array.innerclass;

import java.util.ArrayList;
import java.util.Collection;

public class InnerClassTest {
    public ArrayList<Object> test(){
        class MyList<T> extends ArrayList<T> {
            /**
             * 
             */
            private static final long serialVersionUID = -7658125417511242426L;

            {
                System.out.println("hello world");
            }
        }
        return new MyList<Object>();
    }
    
    public static void main(String[] args) {
        InnerClassTest test=new InnerClassTest();
        ArrayList<Object> list = test.test();
        Collection<Object> c=new ArrayList<>();
        list.addAll(c);
        
    }
}
