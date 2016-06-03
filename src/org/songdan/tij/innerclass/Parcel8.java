package org.songdan.tij.innerclass;


public class Parcel8 {
    /**
     * 创建一个匿名内部类，有参构造器，复写父类的方法
     * 由于不是直接使用参数x，所以x不用被final修饰
     * @param x
     * @return
     */
    public Wrapping wrapping(int x){
        return new Wrapping(x){
            @Override
            public int value() {
                return super.value()+1;
            }
        };
    }
    
    public static void main(String[] args) {
        Parcel8 parcel8 = new Parcel8();
        Wrapping wrapping = parcel8.wrapping(10);
        System.out.println(wrapping.value());
    }
}
