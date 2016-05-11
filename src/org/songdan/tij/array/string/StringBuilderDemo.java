package org.songdan.tij.array.string;


public class StringBuilderDemo {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("abc");
        StringBuilder sb2 = sb.append("a"+":"+"b");
        System.out.println(sb==sb2);
    }
}
