package org.songdan.tij.algorithm.loadbalance;

import java.util.List;

/**
 * 权重轮训算法
 * 1. 所有元素的最大权重 maxWeight
 * 2. 所有元素的权重的最大公约数 gcd
 * 3. 当前权重 currentWeight = maxWeight
 *
 * 1. 从上一次调度实例起，遍历后面的每个实例；
 * 2. 如果当前被遍历的元素的权重大于等于currentWeight，选定当前实例执行
 * 3. 所有的元素遍历一遍后，currentWeight = currentWeight-gcd,如果currentWeight<=0,currentWeight = maxWeight
 * 3.
 *
 * @author: Songdan
 * @create: 2020-05-07 10:55
 **/
public class WeightedRobin implements Robin<Weighted>{

    private Integer currentWeight;

    private Integer maxWeight;

    private Integer gcd;

    private List<Weighted> list;

    private Integer currentIndex;

    public WeightedRobin(List<Weighted> list) {
        this.list = list;
        init();
    }

    private void init(){
        this.currentIndex = -1;
        this.currentWeight = getMaxWeight();
        this.maxWeight = getMaxWeight();
        this.gcd = getGcd();
    }

    private Integer getGcd() {
        int gcd = list.get(0).getWeight();
        for (int i = 1; i < list.size(); i++) {
            gcd = gcd(gcd,list.get(i).getWeight());
        }
        return gcd;
    }

    private Integer getMaxWeight() {
        int maxWeight = list.get(0).getWeight();
        for (int i = 1; i < list.size(); i++) {
            if (maxWeight < list.get(i).getWeight()) {
                maxWeight = list.get(i).getWeight();
            }
        }
        return maxWeight;
    }

    private int gcd(int n1,int n2) {
        if (n1 < n2) {
            int temp = n2;
            n2 = n1;
            n1 = temp;
        }
        while (n1 % n2 != 0) {
            int temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        return n2;
    }

    @Override
    public Weighted next() {
        int index = currentIndex;
        while (true) {
            index = (index + 1) % list.size();
            if (index == 0) {
                currentWeight = currentWeight - getGcd();
                if (currentWeight <= 0) {
                    currentWeight = getMaxWeight();
                }
            }
            Weighted weighted = list.get(index);
            //找到第一个大于等于当前当前权重
            if (weighted.getWeight() >= currentWeight) {
                currentIndex = index;
                return weighted;
            }
        }
    }

}
