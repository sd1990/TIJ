package org.songdan.tij.inherit.son;

import org.songdan.tij.inherit.Person;
import org.songdan.tij.inherit.base.BaseClass;

public class SubClass extends BaseClass {

    private Person p = new Person("son");

    public SubClass(String name) {
        System.out.println("subclass construct ...");
    }

    public void fun(Person p) {
        System.out.println("person fun f");
    }

    public static void main(String[] args) {
        SubClass sb = new SubClass("songdan");
        sb.fun(1);
        sb.fun("songdan");
        sb.fun(sb.p);
    }

    @Override
    public String toString() {
        return "sub class ...";
    }
}
