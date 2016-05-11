package org.songdan.tij.array.generics;

class HasF {

    public void f() {
        System.out.println("HasF.f()");
    }
}

public class Manipulation<T extends HasF> {

    private T t;

    public Manipulation(T t) {
        this.t=t;
    }

    public void fun() {
        t.f();
    }
}
