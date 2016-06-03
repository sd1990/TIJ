package org.songdan.tij.holding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Gerbil {
    
    private int gerbilNumber;

    public Gerbil(int gerbilNumber) {
        super();
        this.gerbilNumber = gerbilNumber;
    }
    
    public void hop(){
        System.out.println("the number "+gerbilNumber+" is hopping ...");
    }
    
    
    
    @Override
    public String toString() {
        return "Gerbil [gerbilNumber=" + gerbilNumber + "]";
    }

    public static void main(String[] args) {
        List<Gerbil> list = new ArrayList<>();
        list.addAll(Arrays.asList(new Gerbil(0),new Gerbil(1),new Gerbil(2),new Gerbil(3),new Gerbil(4)));
        Iterator<Gerbil> iterator = list.iterator();
        while (iterator.hasNext()) {
            Gerbil gerbil = (Gerbil) iterator.next();
            gerbil.hop();
        }
    }
}
