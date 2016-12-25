package org.songdan.tij.algorithm;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionAlgorithmsTest {

    @Test
    public void testUnion() throws Exception {
        List<Integer> firstList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            firstList.add(i+1);
        }
        List<Integer> secondList = new ArrayList<>();
        for (int i = 5; i < 15; i++) {
            secondList.add(i+1);
        }
        List<Integer> union = CollectionAlgorithms.union(firstList, secondList);
        Assert.assertEquals(15,union.size());
    }

    @Test
    public void testUnionRecursion() throws Exception {
        List<Integer> firstList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            firstList.add(i+1);
        }
        List<Integer> secondList = new ArrayList<>();
        for (int i = 5; i < 15; i++) {
            secondList.add(i+1);
        }
        List<Integer> thirdList = new ArrayList<>();
        for (int i = 10; i < 20; i++) {
            thirdList.add(i+1);
        }
        List<Integer> union = CollectionAlgorithms.unionRecursion(firstList, secondList, thirdList);
        Assert.assertEquals(20,union.size());
    }

    @Test
    public void testUnionAllSortedList() {
        List<Integer> firstList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            firstList.add(i+1);
        }
        List<Integer> secondList = new ArrayList<>();
        for (int i = 5; i < 15; i++) {
            secondList.add(i+1);
        }
        List<Integer> unionAllSortedList = CollectionAlgorithms.unionAllSortedList(firstList, secondList);
        System.out.println(unionAllSortedList);
        Assert.assertEquals(20,unionAllSortedList.size());
    }

}