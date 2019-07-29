package org.songdan.tij.math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 字符串测试类
 *
 * @author songdan
 * @version 1.0
 */
public class FloatTest {

    public static void main(String[] args) throws ParseException {
/*        System.out.println(1.0 - 1);
        float f1 = 1.235f;
        // method 1 
        System.out.println(((int) (f1 * 100)) / 100d);
        // method 2 best
        System.out.println(Math.round(f1 * 100) / 100d);
        // method 3
        System.out.println(String.format("%.2f", f1));
        // method 4
        BigDecimal decimal = new BigDecimal(f1);
        System.out.println(decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        //method 5 
        NumberFormat format = new DecimalFormat("#.##");
        System.out.println(format.format(f1));*/
        System.out.println(new BigDecimal(1).setScale(2,RoundingMode.HALF_UP).doubleValue());
        System.out.println(new BigDecimal(1000).setScale(2,RoundingMode.HALF_UP).doubleValue());
        System.out.println(new BigDecimal(0.1).setScale(2,RoundingMode.HALF_UP).doubleValue());
        System.out.println(new BigDecimal(0.11).setScale(2,RoundingMode.HALF_UP).doubleValue());
        System.out.println(new BigDecimal(0.17).setScale(2,RoundingMode.HALF_UP).doubleValue());
        System.out.println(new BigDecimal(0.171).setScale(2,RoundingMode.HALF_UP).doubleValue());
        System.out.println(new BigDecimal(0.178).setScale(2,RoundingMode.HALF_UP).doubleValue());
        System.out.println(System.currentTimeMillis());
        System.out.println(1552311214L * 1000);
        long days = (System.currentTimeMillis() - (1552311214L * 1000)) / (24 * 60 * 60 * 1000);
        System.out.println(days);
        System.out.println(1552311214 * 1000);
        System.out.println(1552311214 * 1000L);
        long i = 1552311214L * 1000;
        System.out.println(i);
        System.out.println(String.valueOf((System.currentTimeMillis() - i) / (24 * 60 * 60 * 1000)));
        NumberFormat numberFormat = new DecimalFormat("#.00");
        System.out.println(stringToDouble(("0")));
        System.out.println(stringToDouble("0.01"));
        System.out.println(stringToDouble("0.011"));
        System.out.println(stringToDouble("0.017"));
        System.out.println(stringToDouble("1.017"));
        System.out.println(stringToDouble("1"));
    }

    public static double stringToDouble(String a){
        double b = Double.valueOf(a);
        DecimalFormat df = new DecimalFormat("#.##");//此为保留1位小数，若想保留2位小数，则填写#.00  ，以此类推
        String temp = df.format(b);
        b = Double.valueOf(temp);
        return b;
    }
}
