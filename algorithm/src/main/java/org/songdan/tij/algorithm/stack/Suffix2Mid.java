package org.songdan.tij.algorithm.stack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

/**
 * 1+2)*3-4)*5-6))) 输出为 （（1+2）*（（3-4）*（5-6）））
 * @author: Songdan
 * @create: 2019-10-31 13:49
 **/
public class Suffix2Mid {

    private static Set<String> operateSet = new HashSet<>();
    private static String left = "(";
    private static String right = ")";

    static {
        operateSet.add("+");
        operateSet.add("-");
        operateSet.add("*");
        operateSet.add("/");
    }

    /**
     * @param str
     * @return
     */
    public static String convert(String str) {
        String[] charArr = str.split("");
        Stack<String> operateStack = new Stack<>();
        Stack<String> valStack = new Stack<>();
        for (String c : charArr) {
            if (right.equals(c)) {
                String v1 = valStack.pop();
                String v2 = valStack.pop();
                String val = left + v2 + operateStack.pop() + v1 + right;
                valStack.push(val);
            } else if (operateSet.contains(c)) {
                operateStack.push(c);
            } else {
                valStack.push(c);
            }
        }
        return valStack.pop();
    }


    public static void main(String[] args) {
        System.out.println(convert("1+2)*3-4)*5-6)))"));
    }

}
