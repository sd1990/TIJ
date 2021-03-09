package org.songdan.tij.anno;

/**
 * @author: Songdan
 * @create: 2020-12-11 21:06
 **/
public class AnnoDemo {

    public static void main(String[] args) {
        System.out.println(Base.class.isAssignableFrom(Sub.class));
        System.out.println(Sub.class.isAssignableFrom(Base.class));
        Demo demo = new Demo();
        System.out.println(demo.getClass().getAnnotation(Base.class));
        Sub annotation = demo.getClass().getAnnotation(Sub.class);
        System.out.println(SubDemo.class.getAnnotation(Sub.class));
    }

    @Sub
    public static class Demo{

    }

    public static class SubDemo extends Demo{

    }

}
