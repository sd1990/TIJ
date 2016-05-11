package org.songdan.tij.array.polymorphic;


public class ParentClass {
    
    public void fun1(){
        System.out.println("parent fun1 go ..");
        fun2();
    }
    
    public void fun2(){
        System.out.println("parent fun2 go ..");
    }
    
    public static void fun(){
        System.out.println("parent fun go ..");
    }
    
    public static void main(String[] args) {
        ParentClass p = new ParentClass();
        p.fun1();
    }
    
}
