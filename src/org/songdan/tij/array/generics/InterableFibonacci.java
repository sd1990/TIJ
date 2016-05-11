package org.songdan.tij.array.generics;

import java.util.Iterator;

public class InterableFibonacci extends Fibonacci implements Iterable<Integer>{
    
    private int n=0;
    
    
    
    
    public InterableFibonacci() {
        super();
        // TODO Auto-generated constructor stub
    }

    public InterableFibonacci(int n) {
        super();
        this.n = n;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
            @Override
            public Integer next() {
                n--;
                return InterableFibonacci.this.next();
            }
            
            @Override
            public boolean hasNext() {
                return n>0;
            }
        };
    }
    
    public static void main(String[] args) {
        InterableFibonacci fib = new InterableFibonacci();
        for (int i = 0; i < 10; i++) {
            System.out.println(fib.next());
        }
        for (Integer integer : new InterableFibonacci(10)) {
            System.out.println(integer);
        }
    }
}
