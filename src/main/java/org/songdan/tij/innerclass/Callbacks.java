package org.songdan.tij.innerclass;

public class Callbacks {

    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        MyIncrement.f(c2);
        //外部类实现接口
        Caller caller1 = new Caller(c1);
        //通过内部类实现闭包的功能,访问caller2访问不到的变量（c2.value）
        Caller caller2 = new Caller(c2.getCallbackReference());
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
}

interface Incrementable {

    void increment();
}

class Callee1 implements Incrementable {

    private int value;

    @Override
    public void increment() {
        value++;
        System.out.println(value);
    }

}

class MyIncrement {

    /**
     * 钩子函数，什么都没有做
     */
    public void increment() {
        System.out.println("Other operation");
    }

    static void f(MyIncrement mi) {
        mi.increment();
    }
}

class Callee2 extends MyIncrement {

    private int value = 0;

    public void increment() {
        super.increment();
        value++;
        System.out.println(value);
    }

    /**
     * 闭包调用外部类
     *
     * @author SONGDAN
     */
    private class Closure implements Incrementable {

        @Override
        public void increment() {
            Callee2.this.increment();
        }

    }

    Incrementable getCallbackReference() {
        return new Closure();
    }
}

class Caller {

    private Incrementable callbackReference;

    Caller(Incrementable cbh) {
        callbackReference = cbh;
    }

    void go() {
        callbackReference.increment();
    }
}























