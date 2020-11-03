package org.songdan.tij.reflect;

import java.lang.reflect.Field;

/**
 * @author: Songdan
 * @create: 2020-07-06 21:33
 **/
public class StaticReflect {

    private static Long id = 100L;

    public static void main(String[] args) {
        Field idField = null;
        try {
            idField = StaticReflect.class.getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        idField.setAccessible(true);
        try {
            Long id = (Long) idField.get(null);
            System.out.println(id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



}
