package org.songdan.tij.generics;

interface Interface1{}

interface Interface2{}

class InterImpl implements Interface1,Interface2{
    private static int count= 0;
    private int index = count++;
    @Override
    public String toString() {
        return "class"+index;
    }
    
}

public class Test25 {
    public  static <K extends Interface1,V extends Interface2> void method1(K k,V v){
        System.out.println(k);
        System.out.println(v);
    }
    public static void main(String[] args) {
        method1(new InterImpl(), new InterImpl());
    }
}
