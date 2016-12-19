package org.songdan.tij.polymorphic;

public class SonClass extends ParentClass {

    @Override
    public void fun2() {
        System.out.println("son fun2 run ...");
    }

    public static void fun() {
        System.out.println("son fun run ...");
    }

    public static void main(String[] args) {
        ParentClass p = new SonClass();
        p.fun1();
        fun();
        p.fun();
    }
}
