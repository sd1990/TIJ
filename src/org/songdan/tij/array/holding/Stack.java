package org.songdan.tij.array.holding;

import java.util.LinkedList;

public class Stack<T> {
    
    private LinkedList<T> list = new LinkedList<>();
    
    /**
     * 弹栈
     * @return
     */
    public T pop(){
       return list.removeFirst();
    }
    
    /**
     * 压栈
     * @param t
     */
    public void push(T t){
        list.addFirst(t);
    }
    
    public boolean isEmpty(){
       return list.isEmpty();
    }
    
    
    @Override
    public String toString() {
        return list.toString();
    }
    
    public T peek(){
        return list.getFirst();
    }
}
