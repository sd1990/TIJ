package org.songdan.tij.math;

import com.google.common.primitives.Ints;

import java.math.BigDecimal;

/**
 * @author: Songdan
 * @create: 2020-04-18 11:41
 **/
public class NumberCalculate {

    public static void main(String[] args) {
        System.out.println("0.1+0.2 with double: "+(0.1d+0.2d));
        System.out.println("0.1+0.2 with float: "+(0.1f+0.2f));
        System.out.println("0.3-0.1 with double: "+(0.3d-0.1d));
        System.out.println("0.3-0.1 with float: "+(0.3f-0.1f));

        Double d = Double.valueOf("9.99999999E8");
        System.out.println(BigDecimal.valueOf(d).toString());
        System.out.println(d);
        Integer integer = Ints.tryParse("123");

    }

}
