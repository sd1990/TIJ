package org.songdan.tij.innerclass;

public class Test11 {

    public static void main(String[] args) {
        Outer2 outer = new Outer2();
        Inter inter = outer.getInner();
        //Outer2.Inner inner = (Outer2.Inner)inter;
    }

}

interface Inter {

    void say();
}

class Outer2 {

    private class Inner implements Inter {

        @Override
        public void say() {
            System.out.println("hello private inner class");
        }

    }

    public Inner getInner() {
        return new Inner();
    }

}