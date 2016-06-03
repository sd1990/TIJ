package org.songdan.tij.generics.coffee;

import org.songdan.tij.generics.Generator;

import java.util.Iterator;
import java.util.Random;

public class CoffeeGenerator implements Generator<Coffee>,Iterable<Coffee>{

    private Class[] types = {Latte.class,Mocha.class,Cappuccino.class,Americano.class,Breve.class};
    
    private int size;
    
    
    public CoffeeGenerator() {
    }
    
    
    
    public CoffeeGenerator(int size) {
        super();
        this.size = size;
    }



    private class CoffeeIterator implements Iterator<Coffee>{

        private int count =size; 
        
        @Override
        public boolean hasNext() {
            return count>0;
        }

        @Override
        public Coffee next() {
            count--;
            return CoffeeGenerator.this.next();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
        
    }
    
    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    private static Random rand = new Random(47);
    
    @Override
    public Coffee next() {
        try {
            return (Coffee) types[rand.nextInt(types.length)].newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        CoffeeGenerator generator = new CoffeeGenerator();
        
        for (int i = 0; i < 5; i++) {
            System.out.println(generator.next());
        }
        for (Coffee coffee : new CoffeeGenerator(5)) {
            System.out.println(coffee);
        }
    }

}
