package org.songdan.tij.calculate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @description: 时间计算
 * @author: Songdan05
 * @create: 2018-06-29 20:31
 **/
public class TimeCalculate {

    public static void main(String[] args) {
        printInt(9);
        printInt(12);

        pringDecimal(9);
        pringDecimal(12);
    }

    private static void printInt(int bewtween) {
        long currentTimeMillis = System.currentTimeMillis();
        long vipEndTimeL = currentTimeMillis +bewtween*3600*1000;
        int betweenDay = (int) (vipEndTimeL-currentTimeMillis) / (1000 * 3600 * 24);
        System.out.println("printInt:"+bewtween+":"+betweenDay);
    }

    private static void pringDecimal(int bewtween) {
        long currentTimeMillis = System.currentTimeMillis();
        BigDecimal vipEndTime = BigDecimal.valueOf(currentTimeMillis).add(BigDecimal.valueOf(bewtween * 3600 * 1000)).divide(BigDecimal.valueOf(1000),RoundingMode.HALF_UP);
        int x = vipEndTime.divide(BigDecimal.valueOf(3600 * 24), RoundingMode.HALF_UP).subtract(BigDecimal.valueOf(currentTimeMillis).divide(BigDecimal.valueOf(1000 * 3600 * 24), RoundingMode.HALF_UP)).intValue();
        System.out.println("printDecimal:"+bewtween+":"+x);
    }

}
