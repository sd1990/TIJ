package org.songdan.tij.array.generics;

/**
 * 水仙花生成器
 * @author SONGDAN
 *
 */
public class Fibonacci implements Generator<Integer>{

    private int count = 0;
    
    @Override
    public Integer next() {
        return fib(count++);
    }

    private int fib(int n){
        if(n<2){
            return 1;
        }else{
            return fib(n-1)+fib(n-2); 
        }
    }
    
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();
        for (int i = 0; i < 10; i++) {
            System.out.println(fib.next());
        }
    }
}
