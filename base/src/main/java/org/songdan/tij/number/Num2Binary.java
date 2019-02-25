package org.songdan.tij.number;

import javafx.util.converter.BigIntegerStringConverter;

import java.math.BigInteger;

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
    }

}
