package org.songdan.tij.array.generics;

import java.util.ArrayList;
import java.util.Random;

public class Product {
    private final int id;
    private String description;
    private double price;
    public Product(int id, String description, double price) {
        super();
        this.id = id;
        this.description = description;
        this.price = price;
    }
    
    @Override
    public String toString() {
        return id+":"+description+":"+price;
    }
    
    public static Generator<Product> generator = new Generator<Product>() {
        private Random rand = new Random(47);
        @Override
        public Product next() {
            return new Product(rand.nextInt(1000),"test",Math.round(rand.nextDouble())+0.99);
        }
    };
    
    public static void main(String[] args) {
        System.out.println(new Store(3, 4, 5));
    }
}

class Shelf extends ArrayList<Product>{
    
    public Shelf(int nProduct) {
        Generators.fill(this, Product.generator, nProduct);
    }
}

class Aisle extends ArrayList<Shelf>{
    public Aisle(int nShelf,int nProduct){
        for (int i = 0; i < nShelf; i++) {
            add(new Shelf(nProduct));
        }
    }
}

class CheckoutStand{};

class Office{}

class Store extends ArrayList<Aisle>{
    private ArrayList<CheckoutStand> checkouts = new ArrayList<>();
    private Office office = new Office();
    public Store(int nAisles,int nShelf,int nProducts){
        for (int i = 0; i < nAisles; i++) {
            add(new Aisle(nShelf, nProducts));
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Aisle aisle : this) {
            for (Shelf shelf : aisle) {
                for (Product product : shelf) {
                    sb.append(product).append("\n");
                }
            }
        }
        return sb.toString();
    }
}