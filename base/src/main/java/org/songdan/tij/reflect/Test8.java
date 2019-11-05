package org.songdan.tij.reflect;

import java.lang.reflect.Field;

public class Test8 {

    private static Integer num = 10;

    public static void printClass(Object obj) {
        Class<? extends Object> class1 = obj.getClass();
        ToyTest.printInfo(class1);
        if (class1.getSuperclass() != null) {
            try {
                printClass(class1.getSuperclass().newInstance());
            }
            catch (InstantiationException e) {
                e.printStackTrace();
                System.exit(1);
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        Toy toy = new FancyToy();
        printClass(toy);
        Field num = Test8.class.getDeclaredField("num");
        num.setAccessible(true);
        System.out.println(num.get(null));
    }
}
