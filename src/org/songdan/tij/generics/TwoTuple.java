package org.songdan.tij.generics;

public class TwoTuple<A, B> {

    private A first;

    private B second;

    public A getFirst() {
        return first;
    }

    public void setFirst(A first) {
        this.first = first;
    }

    public B getSecond() {
        return second;
    }

    public void setSecond(B second) {
        this.second = second;
    }

    public TwoTuple(A first, B second) {
        super();
        this.first = first;
        this.second = second;
    }

    @Override
    public String toString() {
        return "TwoTuple [first=" + first + ", second=" + second + "]";
    }

}
