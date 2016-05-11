package org.songdan.tij.array.innerclass;


public class Test21 {
    public static void main(String[] args) {
        Inter21 inter = new Inter21Impl();
        Inter21.Inner inner = new Inter21.Inner();
        System.out.println(inner.i);
        Inter21.Inner.say();
    }
}

/**
 * 在接口中创建嵌套类
 * @author SONGDAN
 *
 */
interface Inter21{
    void say();
    public static class Inner{
        public static void say(){
            System.out.println("hello inner ...");
        }
        int i ;
    }
}

class Inter21Impl implements Inter21{

    @Override
    public void say() {
        System.out.println("hello world ...");
    }
    
}
