package org.songdan.tij.statictest;

public class StaticTest {

    public static void main(String[] args) {
        Person p1 = StaticDemo.p1;
        System.out.println(StaticDemo.Inner.class);
    }
}
