package org.songdan.tij.string;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * 测试StringBuilder在append的时候引用统一实例
 * 测试String字符串引用相同的字面量，同一个实例
 */
public class StringBuilderDemo {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("abc");
        StringBuilder sb2 = sb.append("a" + ":" + "b");
        System.out.println(sb == sb2);
        String str = "0000";
        String str2 = "0000";
//        System.out.println(str == str2);
//        Scanner scanner = new Scanner(System.in);
//        String consoleStr = scanner.nextLine();
//        System.out.println(consoleStr);
//        System.out.println(str==consoleStr);
        HashSet<Object> set = new HashSet<>();
        System.out.println(set.equals(null));


        String format = String.format("金额:%.2f,百分比:%.2f%%", 3.14456789, 0.14);
        System.out.println(format);

    }
}
