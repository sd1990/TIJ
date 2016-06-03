package org.songdan.tij.enums;

import java.util.Random;

/**
 *
 * 随机存取的工具类
 * Created by PC on 2016/4/4.
 */
public class Enums {

    private static Random random;

    public static <T extends Enum<T>> T random(Class<T> c) {
        return random(c.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        random = new Random();
        return values[random.nextInt(values.length)];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(Enums.random(Activity.class));
        }
    }
}

enum Activity{
    SITTING,LYING,STANDING,HOPPING,RUNNING,DODGING,JUMPING,FAILING,FLYING
}


