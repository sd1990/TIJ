package org.songdan.tij.algorithm.loadbalance;

import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RobinTest {

    @Test
    public void testWeightedRobin() {
        Server google = new Server("www.google.com", 10);
        Server baidu = new Server("www.baidu.com", 2);
        Server bing = new Server("www.bing.com", 2);
        WeightedRobin weightedRobin = new WeightedRobin(Lists.newArrayList(baidu, bing, google));
        for (int i = 0; i < 10; i++) {
            Weighted next = weightedRobin.next();
            System.out.println("index:" + i + ": server:" + next);
        }

    }

    @Test
    public void testSmoothWeightedRobin() {
        Server google = new Server("www.google.com", 10);
        Server baidu = new Server("www.baidu.com", 2);
        Server bing = new Server("www.bing.com", 2);
        SmoothWeightedRobin weightedRobin = new SmoothWeightedRobin(Lists.newArrayList(baidu, bing, google));
        for (int i = 0; i < 10; i++) {
            Weighted next = weightedRobin.next();
            System.out.println("index:" + i + ": server:" + next);
        }

    }

    class Server implements Weighted{

        private String name;

        private Integer weight;

        public Server(String name, Integer weight) {
            this.name = name;
            this.weight = weight;
        }

        @Override
        public Integer getWeight() {
            return weight;
        }

        @Override
        public String toString() {
            return "Server{" +
                    "name='" + name + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}