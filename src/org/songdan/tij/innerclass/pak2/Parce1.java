package org.songdan.tij.innerclass.pak2;

import org.songdan.tij.innerclass.parcel.Inter1;

/**
 * 编程思想内部类章节练习6
 *
 * @author SONGDAN
 */
public class Parce1 {

    /**
     * protected
     *
     * @author SONGDAN
     */
    protected class Inter1Parcel implements Inter1 {

        /**
         * 构造方法公共化
         */
        public Inter1Parcel() {
        }

        @Override
        public void say() {
            System.out.println("hello , i'm inter1Parcel..");
        }

    }

    public Inter1 getInter1() {
        return new Inter1Parcel();
    }
}
