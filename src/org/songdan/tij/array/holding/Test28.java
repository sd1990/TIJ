package org.songdan.tij.array.holding;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Random;

public class Test28 {
    public static void main(String[] args) {
        PriorityQueue<Double> queue = new PriorityQueue<>();
        Random random = new Random();
        for (int i = 0; i < 20; i++) {
            //auto boxing
            Double d = random.nextDouble();
            queue.offer(d);
        }
        Iterator<Double> iterator = queue.iterator();
        /*while (iterator.hasNext()) {
            Double d = (Double) iterator.next();
            System.out.println(d);
            iterator.remove();
        }*/
        /*while(queue.peek()!=null){
            Double poll = queue.poll();
            System.out.println(poll);
        }*/
        for(Double d = queue.poll();d!=null;d=queue.poll()){
            System.out.println(d);
        }
    }
}
