package org.songdan.tij.algorithm.loadbalance;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * 平滑加权轮询算法
 * 初始化：
 *  1.每一个权重节点都有自己的weight，还有以一个当前权重c_weight = weight
 *  2.根据所有的权重节点的weight之和，sumWeight
 *  3.维护一个全局的currentIndex
 *
 * 1. 选出所有节点中最大的当前权重节点，更新其当前权重为c_weight = c_weight-sumweight，更新currentIndex为选中节点的下标
 * 2. 更新节点的当前权重，c_weight = c_weight + weight
 * 3.
 *
 * @author: Songdan
 * @create: 2020-05-07 10:55
 **/
public class SmoothWeightedRobin implements Robin<Weighted>{

    private Integer sumWeight;

    private List<Weighted> list;

    private List<Integer> cWeightList;

    private Integer currentIndex;

    public SmoothWeightedRobin(List<Weighted> list) {
        this.list = list;
        init();
    }

    private void init(){
        this.currentIndex = -1;
        this.sumWeight = getSumWeight();
        this.cWeightList = initCWeight();
    }

    private List<Integer> initCWeight() {
        List<Integer> cWeightList = Lists.newArrayListWithExpectedSize(list.size());
        for (int i = 0; i < list.size(); i++) {
            Weighted weighted = list.get(i);
            cWeightList.add(weighted.getWeight());
        }
        return cWeightList;
    }

    private void reInitCWeight() {
        for (int i = 0; i < cWeightList.size(); i++) {
            Integer cw = cWeightList.get(i);
            cWeightList.set(i, cw + list.get(i).getWeight());
        }
    }

    private Integer getSumWeight() {
        Integer sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).getWeight();
        }
        return sum;
    }

    public Integer getMaxCWeightIndex() {
        Integer maxCWeight = cWeightList.get(0);
        Integer maxIndex = 0;
        for (int i = 1; i < cWeightList.size(); i++) {
            if (cWeightList.get(i)>maxCWeight) {
                maxCWeight = cWeightList.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }


    @Override
    public Weighted next() {
        Integer maxCWeightIndex = getMaxCWeightIndex();
        currentIndex = maxCWeightIndex;
        Integer cWeight = cWeightList.get(maxCWeightIndex);
        cWeightList.set(maxCWeightIndex, cWeight - sumWeight);
        reInitCWeight();
        return list.get(currentIndex);

    }

}
