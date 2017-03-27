package org.songdan.tij.inherit;

/**
 * 测试在继承体系中从父类继承的方法和重写父类的方法的区别
 * Created by PC on 2016/6/12.
 */
public class Child extends Father {

    protected String value = "456";

    private String name = "child";

    @Override
    public String name() {
        return name;
    }

    public static void main(String[] args) {
        Child child = new Child();
        System.out.println(child.value());
        System.out.println(child.name());
        Father father = new Father();
        System.out.println(father.value());
        System.out.println(father.name());
    }
}

class Father {

    protected String value = "123";

    private String name = "father";

    public String value() {
        return value;
    }

    public String name() {
        return name;
    }
}
