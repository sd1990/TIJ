package org.songdan.tij.array.math;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 字符串测试类
 * @author songdan
 * @version 1.0
 *
 */
public class FloatTest {
    public static void main(String[] args) {
        System.out.println(1.0-1);
        float f1=1.235f;
        // method 1 
        System.out.println(((int)(f1*100))/100d);
        // method 2 best
        System.out.println(Math.round(f1*100)/100d);
        // method 3
        System.out.println(String.format("%.2f", f1));
        // method 4
        BigDecimal decimal = new BigDecimal(f1);
        System.out.println(decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        //method 5 
        NumberFormat format = new DecimalFormat("#.##");
        System.out.println(format.format(f1));
    }
}
