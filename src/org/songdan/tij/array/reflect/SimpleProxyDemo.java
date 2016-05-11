package org.songdan.tij.array.reflect;

interface Interface{
    public void dosomething();
    
    public void somethingElse(String args);
}

class RealObject implements Interface{

    @Override
    public void dosomething() {
        System.out.println("do something ...");
    }

    @Override
    public void somethingElse(String args) {
        System.out.println("do something else:"+args);
    }
    
}

class SimpleProxy implements Interface{

    private Interface proxied;
    
    private int doSomethingTimes;

    private int doSomethingElseTimes;
    
    public SimpleProxy() {
    }
    
    SimpleProxy(Interface realObject){
        proxied=realObject;
        doSomethingTimes=0;
        doSomethingElseTimes=0;
    }
    @Override
    public void dosomething() {
        doSomethingTimes++;
        System.out.println(doSomethingTimes);
        proxied.dosomething();
    }

    @Override
    public void somethingElse(String args) {
        doSomethingElseTimes++;
        System.out.println(doSomethingElseTimes);
        proxied.dosomething();
    }
    
    
    
}

public class SimpleProxyDemo{
    public static void consumer(Interface inter){
        inter.dosomething();
        inter.somethingElse("tim dunc");
    }
    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}