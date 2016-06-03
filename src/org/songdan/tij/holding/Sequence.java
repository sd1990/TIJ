package org.songdan.tij.holding;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 学会使用面向对象的思想
 * @author SONGDAN
 *
 */
public class Sequence {
    
    private ArrayList<Object> list = new ArrayList<>();
    
    public void add(Object obj){
        list.add(obj);
    }
    
    public Iterator iterator(){
        return list.iterator();
    }
}
