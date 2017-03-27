package org.songdan.tij.trycatch;

/**
 * 测试在finally中如果有return操作，try中的异常不会被抛出
 * Created by SongDan on 2017/3/13.
 */
public class TryCatchDemo {

    public static void main(String[] args) {
        testThrowException();
    }

    public static void testThrowException() {
        try{
            int i = 1 / 0;
        }finally {
            //异常信息不会抛出
            return;
        }
    }

}
