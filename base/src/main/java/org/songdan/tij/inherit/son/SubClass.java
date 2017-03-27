package org.songdan.tij.inherit.son;

import org.songdan.tij.inherit.Person;
import org.songdan.tij.inherit.base.BaseClass;

/**
 * 测试在继承体系中，对象的实例化过程，以及父类构造器中调用子类重写的方法的问题（不要在父类构造器中调用可以被子类重写的方法）
 */
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
        System.out.println("this 引用在子类中的引用指向："+this);
        System.out.println("this.p 字段在子类中的值为:" + this.p);
        System.out.println("subclass construct end ...");
    }

    @Override
    public void fun(String p) {
        System.out.println("sub class fun run ...");
    }

    public static void main(String[] args) {
        SubClass sb = new SubClass("songdan");
    }

    @Override
    public String toString() {
        return "sub class";
    }
}
