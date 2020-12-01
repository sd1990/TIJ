package org.songdan.tij.number;

import javafx.util.converter.BigIntegerStringConverter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.NumberFormat;

/**
 * @author: Songdan
 * @create: 2019-02-22 13:16
 **/
public class Num2Binary {


    public String toBinary(int num) {
        return BigInteger.valueOf(num).toString(2);
    }

    public int ofBinary(String binary) {
        return new BigInteger(binary, 2).intValue();
    }

    public String toBinaryCustom(int num) {
        StringBuilder sb = new StringBuilder();
        while (num!=0) {
            sb.insert(0,num % 2);
            num = num / 2;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Num2Binary num2Binary = new Num2Binary();
        System.out.println(num2Binary.toBinary(53));
        System.out.println(num2Binary.toBinaryCustom(53));

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        numberFormat.setMinimumFractionDigits(0);
        numberFormat.setMaximumFractionDigits(2);

        System.out.println(numberFormat.format(100001.010001));
        numberFormat.setGroupingUsed(true);
        System.out.println(numberFormat.format(100001.010001));
        System.out.println(String.valueOf(12.10));

        System.out.println(BigDecimal.valueOf(1.1122233344455567E49).longValue());
        System.out.println(BigDecimal.valueOf(11.001).stripTrailingZeros().toPlainString());
    }

}
