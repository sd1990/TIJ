package org.songdan.tij.holding;

public class Test15 {

    public static void main(String[] args) {
        String source = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---";
        char[] charArray = source.toCharArray();
        Stack stack = new Stack<>();
        for (int i = 0; i < charArray.length; ) {
            char c = charArray[i];
            switch (c) {
                case '+':
                    stack.push(charArray[++i]);
                    break;
                case '-':
                    stack.pop();
                    i++;
                    break;
                default:
                    i++;
            }
        }
        System.out.println(stack);
        System.out.println(evaluate(source));
    }

    private static Stack evaluate(String expr) {
        Stack<Character> stack = new Stack();
        char data[] = expr.toCharArray();
        for (int i = 0; i < data.length; ) {
            switch (data[i++]) {
                case '+':
                    stack.push(data[i++]);
                    break;
                case '-':
                    System.out.print(stack.pop());
            }
        }
        return stack;
    }

}
