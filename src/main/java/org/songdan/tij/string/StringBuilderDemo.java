package org.songdan.tij.string;

import java.util.Scanner;

public class StringBuilderDemo {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("abc");
        StringBuilder sb2 = sb.append("a" + ":" + "b");
        System.out.println(sb == sb2);
        String str = "0000";
        String str2 = "0000";
        System.out.println(str == str2);
        Scanner scanner = new Scanner(System.in);
        String consoleStr = scanner.nextLine();
        System.out.println(consoleStr);
        System.out.println(str==consoleStr);
    }
}
