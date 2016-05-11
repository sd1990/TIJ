package org.songdan.tij.array.generics.coffee;

/**
 * 基类
 * @author SONGDAN
 *
 */
public class Coffee {
    private static long counter = 0;
    private final long id = counter++;
    public String toString(){
        return getClass().getSimpleName()+" "+id;
    }
}
