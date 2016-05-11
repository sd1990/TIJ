package org.songdan.tij.array.generics;

import java.util.Iterator;

/**
 * 使用组合来完成适配
 * @author SONGDAN
 *
 */
public class IteratorFibonacci implements Iterable<Integer>{

    private Fibonacci fib;
    
    private int n=0;
    

    public IteratorFibonacci(Fibonacci fib) {
        super();
        this.fib = fib;
    }

    
    public Integer next(){
        return fib.next();
    }
    
    

    public IteratorFibonacci(Fibonacci fib, int n) {
        super();
        this.fib = fib;
        this.n = n;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            
            private int count = n;
            
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
            
            @Override
            public Integer next() {
                count--;
                return IteratorFibonacci.this.next();
            }
            
            @Override
            public boolean hasNext() {
                return count>0;
            }
        };
    }

    
    public static void main(String[] args) {
        InterableFibonacci f = new InterableFibonacci();
        IteratorFibonacci fib = new IteratorFibonacci(f);
        for (int i = 0; i < 10; i++) {
            System.out.println(fib.next());
        }
        for (Integer integer : new IteratorFibonacci(f, 10)) {
            System.out.println(integer);
        }
    }
}
