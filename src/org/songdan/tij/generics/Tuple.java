package org.songdan.tij.generics;

public class Tuple {

    public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
        return new TwoTuple<A, B>(a, b);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
        return new ThreeTuple<A, B, C>(a, b, c);
    }

    static class TupleTest {

        static TwoTuple<String, Integer> f() {
            return Tuple.tuple("hi", 47);
        }

        static TwoTuple f2() {
            return f();
        }

        public static void main(String[] args) {
            TwoTuple<String, Integer> ttsi = f();
            System.out.println(ttsi);
        }
    }

    public static void main(String[] args) {
        TupleTest.main(null);
    }
}
