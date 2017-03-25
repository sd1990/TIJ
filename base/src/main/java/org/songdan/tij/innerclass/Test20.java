package org.songdan.tij.innerclass;

public class Test20 {

    public static void main(String[] args) {
        Inter20 inter = new InterImpl();
        Inter20.Inner inner = new Inter20.Inner();
        System.out.println(inner.i);
    }
}

/**
 * 在接口中创建嵌套类
 *
 * @author SONGDAN
 */
interface Inter20 {

    void say();

    public static class Inner {

        int i;
    }
}

class InterImpl implements Inter20 {

    @Override
    public void say() {
        System.out.println("hello world ...");
    }

}
