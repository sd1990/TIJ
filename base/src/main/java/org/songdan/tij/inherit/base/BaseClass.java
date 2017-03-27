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
        System.out.println("base class construct begin ...");
        System.out.println("this 引用在父类中的指向 ："+this);
        System.out.println("this.p 字段在父类中的值为:" + this.p);
        fun("");
        System.out.println("base object construct end ....");
    }

    public void fun(int i) {
        System.out.println("int run ()");
    }

    public void fun(String str) {
        System.out.println("base class fun run ()");
    }

    @Override
    public String toString() {
        return "super";
    }
}
