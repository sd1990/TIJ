package org.songdan.tij.inherit.son;

import org.songdan.tij.inherit.Person;
import org.songdan.tij.inherit.base.BaseClass;

public class SubClass extends BaseClass {

    private static Person staticP = new Person("static son");
    private Person p = new Person("son");

    static{
        System.out.println("sub class static code");
    }

    {
        System.out.println("sub class construct code");
    }

    public SubClass(String name) {
        System.out.println("subclass construct begin ...");
        System.out.println(this);
        System.out.println(this.p);
        System.out.println("subclass construct ...");
    }

    public void fun(Person p) {
        System.out.println("person fun f");
    }

    public static void main(String[] args) {
        SubClass sb = new SubClass("songdan");
        /*sb.fun(1);
        sb.fun("songdan");
        sb.fun(sb.p);*/
    }

    @Override
    public String toString() {
        return "sub class";
    }
}
