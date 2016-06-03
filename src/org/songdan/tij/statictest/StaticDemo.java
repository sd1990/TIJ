package org.songdan.tij.statictest;


public class StaticDemo {
    static Person p1=new Person("1");
    static Person p2=new Person("2");
    public static class Inner{
        static Person p3=new Person("2");
    }
}
