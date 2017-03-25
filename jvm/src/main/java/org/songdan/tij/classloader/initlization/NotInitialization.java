package org.songdan.tij.classloader.initlization;

/**
 * 类加载过程中，初始化阶段，被动引用实例
 * @author Songdan
 * @date 2016/10/21 14:00
 */
public class NotInitialization {
    public static void main(String[] args) {
        //子类引用父类的static field，只初始化父类，不初始化子类
//        System.out.println(SubClass.value);
        //创建父类的数组不初始化父类
//        SuperClass[] superClasses = new SuperClass[10];
        //引用本类的常量，不初始化本类，编译器就已加入了常量池
        System.out.println(ConstClass.HELLO_WORLD);
    }
}
class SuperClass{

    static String value = "super value";

    static {
        System.out.println("super class init");
    }
}

class SubClass extends SuperClass{
    static{
        System.out.println("sub class init");
    }
}

class ConstClass{
    static {
        System.out.println("ConstClass init");
    }

    public static final String HELLO_WORLD = "hello world ";
}