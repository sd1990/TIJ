package org.songdan.tij.innerclass;


public class Test23 {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        A a3 = new A();
        B b = new B(3);
        b.add(a1.getU());
        b.add(a2.getU());
        b.add(a3.getU());
        b.use();
        b.empty();
    }
}

interface U{
    void method1();
    void method2();
    void method3();
}

class A{
    public U getU(){
        return new U() {
            
            @Override
            public void method3() {
                System.out.println("method3 run ...");
            }
            
            @Override
            public void method2() {
                System.out.println("method2 run ...");
            }
            
            @Override
            public void method1() {
                System.out.println("method1 run ...");
            }
        };
    }
}

class B{
    private U[] us;
    private int index = 0;
    private int length;
    
    
    public B(int length) {
        super();
        us = new U[length];
        this.length=length;
    }

    public void add(U u){
        if(index<length){
            us[index++]=u;
        }
    }
    
    public void empty(){
        for (U u : us) {
            System.out.println("我挂了。。。");
            u=null;
        }
    }
    
    public void use(){
        for (U u : us) {
            u.method1();
            u.method2();
            u.method3();
        }
    }
}















































