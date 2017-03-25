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
                    if (SMALL_LEFT.equals(stack.pop())) {
                        break;
                    }
                    else {
                        return false;
                    }
                case SQUARE_RIGHT:
                    if (SQUARE_LEFT.equals(stack.pop())) {
                        break;
                    }
                    else {
                        return false;
                    }
                case BRACE_RIGHT:
                    if (BRACE_LEFT.equals(stack.pop())) {
                        break;
                    }
                    else {
                        return false;
                    }
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static boolean groupCheck(String parenthesisStr) {
        if (parenthesisStr.length()%2!=0) {
            return false;
        }
        Stack<String> stack = new Stack<>();
        for (String parenthesis : parenthesisStr.split("")) {
            switch (parenthesis) {
                case SMALL_LEFT:
                    stack.push(SMALL_RIGHT);
                    break;
                case SQUARE_LEFT:
                    stack.push(SQUARE_RIGHT);
                    break;
                case BRACE_LEFT:
                    stack.push(BRACE_RIGHT);
                    break;
                case SMALL_RIGHT:
                case SQUARE_RIGHT:
                case BRACE_RIGHT:
                    if (!parenthesis.equals(stack.pop())) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return stack.isEmpty();
    }

    public static boolean groupCheckByReplace(String s) {
        int len;
        do {
            len = s.length();
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        } while (len != s.length());
        return s.length() == 0;
    }

    public static boolean groupCheckOld(String s){
        Stack<Character> stack = new Stack<Character>();
        try {
            for(char c:s.toCharArray()) {
                if (c == '(')
                    stack.push(')');
                else if (c == '[')
                    stack.push(']');
                else if (c == '{')
                    stack.push('}');
                else if ( c == ')' || c == ']' || c == '}') {
                    char top = stack.pop();
                    if (top != c) return false;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(check("{[(())()]}"));
        System.out.println(check("(("));
        System.out.println(groupCheck("{[(())()]}"));
        System.out.println(groupCheck("(("));
        System.out.println(groupCheckByReplace("{[(())()]}"));
        System.out.println(groupCheckByReplace("(("));
    }

}
