package org.songdan.tij.inherit;


public class Person {
    private String name;

    public Person(String name) {
        super();
        System.out.println("name is : "+name);
    }
    public static void main(String[] args) {
        Demo.main(new String[]{});
    }
}

class Demo{
    private int a = 0;
    
    public Demo() {
        a++;
        b++;
    }
    private int b;
    
    public static void main(String[] args) {
        Demo demo = new Demo();
        System.out.println(demo.a);
        System.out.println(demo.b);
    }
}