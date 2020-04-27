package org.songdan.tij.codewars.sku_3;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 *
 * Create a simple calculator that given a string of operators (), +, -, *, / and numbers separated by spaces returns the value of that expression
 *
 * Example:
 *
 * Calculator.evaluate("2 / 2 + 3 * 4 - 6") // => 7
 * Remember about the order of operations! Multiplications and divisions have a higher priority and should be performed left-to-right. Additions and subtractions have a lower priority and should also be performed left-to-right.
 *
 * @author: Songdan
 * @create: 2020-04-26 16:51
 **/
public class Calculator {

    static String LEFT_BRACKET = "(";
    static String RIGHT_BRACKET = ")";

    static Set<String> operates = new HashSet<String>(){
        {
            add("+");
            add("-");
            add("*");
            add("/");
        }
    };

    public static Double evaluate(String expression) {
        if(expression == null || expression.length()==0){
            return -1.0;
        }
        Stack<ExecuteStack> bkStack = new Stack<>();
        bkStack.push(new ExecuteStack());
        char[] chs = expression.toCharArray();
        StringBuilder num = new StringBuilder();
        for (int i = 0; i < chs.length; i++) {
            ExecuteStack executeStack = bkStack.peek();
            String s = String.valueOf(chs[i]);
            if (" ".equals(s)) {
                continue;
            }
            //处理负数
            if ("-".equals(s)) {
                if (executeStack.flag || executeStack.i == 0) {
                    num.append(s);
                    continue;
                }
            }
            if (LEFT_BRACKET.equals(s)) {
                bkStack.push(new ExecuteStack());
                continue;
            }
            if (RIGHT_BRACKET.equals(s)) {
                ExecuteStack popExecuteStack = bkStack.pop();
                if (num.length() > 0) {
                    popExecuteStack.numStack.push(Double.valueOf(num.toString()));
                    num = new StringBuilder();
                }
                Stack<String> exeOpStack = popExecuteStack.opStack;
                Stack<Double> exeNumStack = popExecuteStack.numStack;
                while (!exeOpStack.isEmpty()) {
                    String op = exeOpStack.pop();
                    Double num2 = exeNumStack.pop();
                    Double num1 = exeNumStack.pop();
                    exeNumStack.push(calculator0(num1, num2, op));
                }
                ExecuteStack topExeStack = bkStack.peek();
                topExeStack.numStack.push(exeNumStack.pop());
                continue;
            }
            if (operates.contains(s)) {
                if (num.length()>0) {
                    executeStack.numStack.push(Double.valueOf(num.toString()));
                    num = new StringBuilder();
                }
                //判断优先级，如果当前优先级高于栈顶元素，直接入栈；
                // 如果不高于栈顶元素，栈顶元素出栈计算，然后当前操作符入栈
                while (!executeStack.opStack.isEmpty() && !isHigher(s,executeStack.opStack.peek())) {
                    String op = executeStack.opStack.pop();
                    Double num2 = executeStack.numStack.pop();
                    Double num1 = executeStack.numStack.pop();
                    executeStack.numStack.push(calculator0(num1, num2, op));
                }
                executeStack.opStack.push(s);
                executeStack.flag = true;
            } else {
                num.append(s);
                executeStack.flag = false;
            }
            executeStack.i++;
        }
        ExecuteStack executeStack = bkStack.pop();
        if (num.length() > 0) {
            executeStack.numStack.push(Double.valueOf(num.toString()));
        }
        while (!executeStack.opStack.isEmpty()) {
            String op = executeStack.opStack.pop();
            Double num2 = executeStack.numStack.pop();
            Double num1 = executeStack.numStack.pop();
            executeStack.numStack.push(calculator0(num1, num2, op));
        }
        // your code here
        return executeStack.numStack.pop();
    }

    static class ExecuteStack{
        Stack<Double> numStack = new Stack<>();
        Stack<String> opStack = new Stack<>();
        boolean flag = false;
        int i = 0;
    }

    private static boolean isHigher(String currentOp,String topOp) {
        Op op = Op.of(currentOp);
        switch (topOp) {
            case "+":
                return op.addHigh;
            case "-":
                return op.minuteHigh;
            case "*":
                return op.mulHigh;
            case "/":
                return op.divideHigh;
        }
        throw new IllegalArgumentException();
    }

    private static Double calculator0(Double num1, Double num2, String op) {
        switch (op) {
            case "+":
                return BigDecimal.valueOf(num1).add(BigDecimal.valueOf(num2)).doubleValue();
            case "-":
                return BigDecimal.valueOf(num1).subtract(BigDecimal.valueOf(num2)).doubleValue();
            case "*":
                return BigDecimal.valueOf(num1).multiply(BigDecimal.valueOf(num2)).doubleValue();
            case "/":
                return BigDecimal.valueOf(num1).divide(BigDecimal.valueOf(num2)).doubleValue();
        }
        throw new IllegalArgumentException();
    }

    enum Op{
        PLUS("+",false,false,false,false),
        MINUTE("-",false,false,false,false),
        MULTIPLY("*",true,true,false,false),
        DIVIDE("/",true,true,false,false);

        private String op;

        private boolean addHigh;
        private boolean minuteHigh;
        private boolean mulHigh;
        private boolean divideHigh;

        Op(String op, boolean addHigh, boolean minuteHigh, boolean mulHigh, boolean divideHigh) {
            this.op = op;
            this.addHigh = addHigh;
            this.minuteHigh = minuteHigh;
            this.mulHigh = mulHigh;
            this.divideHigh = divideHigh;
        }

        public static Op of(String s) {
            for (Op op : values()) {
                if (op.op.equals(s)) {
                    return op;
                }
            }
            return null;
        }


    }
}
