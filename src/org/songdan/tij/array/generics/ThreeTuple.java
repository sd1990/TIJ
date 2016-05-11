package org.songdan.tij.array.generics;


public class ThreeTuple<A,B,C> extends TwoTuple<A, B> {

    private C third;
    
    public ThreeTuple(A a,B b,C c) {
        super(a,b);
        third=c;
    }

    @Override
    public String toString() {
        return "ThreeTuple [third=" + third + "]";
    }
    
    
}
