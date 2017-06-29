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
        //禁止在父类构造函数中调用子类重写的方法，因为此时子类还没有完成初始化，会出现不可预知的结果
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
