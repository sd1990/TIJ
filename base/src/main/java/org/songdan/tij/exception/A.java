package org.songdan.tij.exception;

/**
 * Created by SongDan on 2017/4/15.
 */
public class A {

    static B b = new B();

    public static void main(String[] args) {
        //删除B的class文件，会导致由于ClassNotFoundException导致的NoClassDefFoundException
        //删除B的class文件，然后将C的class替换为B的class，会直接导致java.lang.NoClassDefFoundError: org/songdan/tij/exception/B (wrong name: org/songdan/tij/exception/C)
        System.out.println("测试NoClassDefFoundError");
    }

}
