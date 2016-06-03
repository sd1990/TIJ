package org.songdan.tij.generics;


public class LinkedStack <T> {
    
    
    /**
     * 静态内部类
     * @author SONGDAN
     *
     * @param <U>
     */
    private static class Node<U>{
        U value;
        Node<U> next;
        Node(){
            value = null;
            next=null;
        }
        Node(U value ,Node<U> next){
            this.value=value;
            this.next=next;
        }
        
        boolean end(){
            return value == null && next==null;
        }
    }
    /**首节点*/
    private Node<T> top = new Node<>();
    
    public void push(T item){
        top = new Node<>(item, top);
    }
    
    public T pop(){
        T t = top.value;
        if(!top.end()){
            top = top.next;
        }
        return t;
    }
    
    
}
