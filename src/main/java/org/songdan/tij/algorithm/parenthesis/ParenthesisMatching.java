package org.songdan.tij.algorithm.parenthesis;

import org.songdan.tij.holding.Stack;

/**
 * 括号匹配算法
 * @author Songdan
 * @date 2017/2/14 10:50
 */
public class ParenthesisMatching {

    public static final String SMALL_LEFT = "(";
    public static final String SMALL_RIGHT = ")";
    public static final String SQUARE_LEFT = "[";
    public static final String SQUARE_RIGHT = "]";
    public static final String BRACE_LEFT = "{";
    public static final String BRACE_RIGHT = "}";

    /**
     * 校验括号是否匹配
     * @param parenthesisStr
     * @return
     */
    public static boolean check(String parenthesisStr) {
        String[] parenthesises = parenthesisStr.split("");
        Stack<String> stack = new Stack<>();
        for (String parenthesise : parenthesises) {
            switch (parenthesise) {
                case SMALL_LEFT:
                    //与左方括号处理逻辑相同
                case BRACE_LEFT:
                    //与左方括号处理逻辑相同
                case SQUARE_LEFT:
                    stack.push(parenthesise);
                    break;
                case SMALL_RIGHT:
                    String peek = stack.peek();
                    if (SMALL_LEFT.equals(peek)) {
                        stack.pop();
                        break;
                    }
                    else {
                        return false;
                    }
                case SQUARE_RIGHT:
                    peek = stack.peek();
                    if (SQUARE_LEFT.equals(peek)) {
                        stack.pop();
                        break;
                    }
                    else {
                        return false;
                    }
                case BRACE_RIGHT:
                    peek = stack.peek();
                    if (BRACE_LEFT.equals(peek)) {
                        stack.pop();
                        break;
                    }
                    else {
                        return false;
                    }
                default:
                    break;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(check("{[(()]}"));
    }

}
