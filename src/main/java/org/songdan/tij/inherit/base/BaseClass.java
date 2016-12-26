package org.songdan.tij.inherit.base;

import org.songdan.tij.inherit.Person;

public class BaseClass {

    private Person p = new Person("father");

    public BaseClass() {
        super();
        System.out.println(this);
        System.out.println("base object construct...");
    }

    public void fun(int i) {
        System.out.println("int run ()");
    }

    public void fun(String str) {
        System.out.println("string run ()");
    }

    @Override
    public String toString() {
        return "super";
    }
}
