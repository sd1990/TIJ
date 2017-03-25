package org.songdan.tij.algorithm.polynomial;

import java.util.Collections;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 多项式计算
 * Created by SongDan on 2017/2/7.
 */
public class PolynomialCalculate {

    public static LinkedList<Polynomial> add(LinkedList<Polynomial> p1, LinkedList<Polynomial> p2) {
        Polynomial a = null,b = null;
        PolynomialCompartor compartor = new PolynomialCompartor();
        Collections.sort(p1, compartor);
        Collections.sort(p2, compartor);
        LinkedList<Polynomial> result = new LinkedList<>();
        ListIterator<Polynomial> aIterator = p1.listIterator();
        ListIterator<Polynomial> bIterator = p2.listIterator();
        boolean aFlag = true;
        boolean bFlag = true;
        while (aIterator.hasNext()&&bIterator.hasNext()) {
            /*
            如果p1的元素的幂值小于p2元素的幂值，将p1当前迭代的元素添加到result中，p1向下迭代；
            如果p1的元素的幂值大于p2元素的幂值，将p2的元素添加倒result中，p2向下迭代
            如果p1的元素的幂值等于p2元素的幂值，将两个元素的系数相加
                如果相加的结果为0，p1向下迭代，p2向下迭代
                如果相加的结果不为0，将得到的结果和幂值生成新的元素添加到result中，p1向下迭代，p2向下迭代
             */
            if (aFlag) {
                a = aIterator.next();
            }
            if (bFlag) {
                b = bIterator.next();
            }

            if (a.getPower() < b.getPower()) {
                result.add(a);
                aFlag = true;
                bFlag = false;
            }
            else if (a.getPower() > b.getPower()) {
                result.add(b);
                aFlag = false;
                bFlag = true;
            }
            else {
                double v = a.getCoefficient() + b.getCoefficient();
                if (v != 0d) {
                    result.add(new Polynomial(v, a.getPower()));
                }
                aFlag = true;
                bFlag = true;
            }
        }
        if (aIterator.hasNext()) {
            while (aIterator.hasNext()) {
                result.add(aIterator.next());
            }
        }
        if (bIterator.hasNext()) {
            while (bIterator.hasNext()) {
                result.add(bIterator.next());
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Polynomial a1 = new Polynomial(7,0);
        Polynomial a2 = new Polynomial(3,1);
        Polynomial a3 = new Polynomial(9,8);
        Polynomial a4 = new Polynomial(5,17);
        Polynomial b1 = new Polynomial(8,1);
        Polynomial b2 = new Polynomial(22,7);
        Polynomial b3 = new Polynomial(-9,8);
        LinkedList<Polynomial> p1 = new LinkedList<>();
        LinkedList<Polynomial> p2 = new LinkedList<>();
        p1.add(a1);
        p1.add(a2);
        p1.add(a3);
        p1.add(a4);
        p2.add(b1);
        p2.add(b2);
        p2.add(b3);
        System.out.println(add(p1,p2));
    }

}
