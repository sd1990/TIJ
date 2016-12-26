package org.songdan.tij.holding;

import java.util.LinkedList;
import java.util.Queue;

public class Test27 {

    public static void main(String[] args) {
        Commond c1 = new Commond("abc");
        Commond c2 = new Commond("bcd");
        CommondFill cf = new CommondFill();
        cf.fillQueue(c1);
        Queue<Commond> fillQueue = cf.fillQueue(c2);
        for (Commond commond : fillQueue) {
            commond.operation();
        }

    }
}

class Commond {

    private String name;

    public void operation() {
        System.out.println(name);
    }

    public Commond(String name) {
        super();
        this.name = name;
    }

}

class CommondFill {

    Queue<Commond> queue = new LinkedList<>();

    public Queue<Commond> fillQueue(Commond commond) {
        queue.add(commond);
        return queue;
    }
}
