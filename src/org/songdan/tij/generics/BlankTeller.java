package org.songdan.tij.generics;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class Customer{
    private static long counter = 1;
    private final long id = counter++;
    private Customer(){
        
    }
    public static Generator<Customer> generator(){
        return new Generator<Customer>() {
            
            @Override
            public Customer next() {
                return new Customer();
            }
        };
    }
    @Override
    public String toString() {
        return "customer :"+id;
    }
}

class Teller{
    private static long counter=1;
    private final long id=counter++;
    private Teller(){};
    public static Generator<Teller> generator=new Generator<Teller>() {
        
        @Override
        public Teller next() {
            return new Teller();
        }
    };
    @Override
    public String toString() {
        return "teller :"+id;
    }
}

public class BlankTeller {
    public static void main(String[] args) {
        Random random = new Random(47);
        Queue<Customer> line = new LinkedList<>();
        Generators.fill(line, Customer.generator(), 15);
        ArrayList<Teller> tellers = new ArrayList<Teller>();
        Generators.fill(tellers, Teller.generator, 4);
        for (Customer customer : line) {
            System.out.println(customer+"  "+tellers.get(random.nextInt(tellers.size())));
        }
    }
}
