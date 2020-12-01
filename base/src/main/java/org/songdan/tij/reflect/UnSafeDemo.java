package org.songdan.tij.reflect;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: Songdan
 * @create: 2020-11-30 13:54
 **/
public class UnSafeDemo {

    private Long field1;
    private Long field2;
    private Long field3;
    private Integer field4;

    private static final Class c = UnSafeDemo.class;

    public static Unsafe createUnsafe() {
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void testFieldSwap() {
        try {
            Unsafe unsafe = createUnsafe();
            System.out.println(unsafe.objectFieldOffset(c.getDeclaredField("field1")));
            System.out.println(createUnsafe().objectFieldOffset(c.getDeclaredField("field2")));
            System.out.println(createUnsafe().objectFieldOffset(c.getDeclaredField("field3")));
            System.out.println(createUnsafe().objectFieldOffset(c.getDeclaredField("field4")));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        UnSafeDemo unSafeDemo = new UnSafeDemo();
        unSafeDemo.testFieldSwap();
    }

}
