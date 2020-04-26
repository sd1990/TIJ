package org.songdan.tij.trycatch;

/**
 * 测试在finally中如果有return操作，try中的异常不会被抛出
 * Created by SongDan on 2017/3/13.
 */
public class TryCatchDemo {

    public static void main(String[] args) {
        try {

            testThrowException();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void testThrowException() {
        try{
            System.out.println("hello world!!!");
        }finally {
            //异常信息不会抛出
            throw new RuntimeException("aaa");
        }
    }

}
