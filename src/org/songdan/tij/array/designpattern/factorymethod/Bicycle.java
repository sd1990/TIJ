package org.songdan.tij.array.designpattern.factorymethod;


public class Bicycle extends Cycle{

    @Override
    public void move() {
        System.out.println("bicycle run ..");
    }
    
    public static CycleFactory factory = new CycleFactory() {
        
        @Override
        public Cycle getFactory() {
            return new Bicycle();
        }
    };
}
