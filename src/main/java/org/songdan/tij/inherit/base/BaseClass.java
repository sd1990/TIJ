package org.songdan.tij.inherit.base;

import org.songdan.tij.inherit.Person;

public class BaseClass {

    private static Person staticP = new Person("static father");

    private Person p = new Person("father");

    static{
        System.out.println("base class static code");
    }

    {
        System.out.println("base class construct code");
    }

    public BaseClass() {
        super();
        System.out.println("baseclass construct begin ...");
        System.out.println(this);
        System.out.println(this.p);
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
