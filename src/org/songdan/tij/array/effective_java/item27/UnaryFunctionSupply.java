package org.songdan.tij.array.effective_java.item27;

/**
 * Created by PC on 2016/4/26.
 */
public class UnaryFunctionSupply {

    private static UnaryFunction<Object> IDENTITY_FUNCTION = new UnaryFunction<Object>() {

        @Override
        public Object apply(Object arg) {
            return arg;
        }
    };


    public static <T> UnaryFunction<T> identityFunction() {
        return (UnaryFunction<T>) IDENTITY_FUNCTION;
    }

    public static void main(String[] args) {
        String[] strings = { "abc", "bcd", "cdf" };
        UnaryFunction<String> sameString = identityFunction();
        for (String string : strings) {
            System.out.println(sameString.apply(string));
        }
        Number[] numbers = {1,2,3,4,5 };
        UnaryFunction<Number> sameNum = identityFunction();
        for (Number number : numbers) {
            System.out.println(sameNum.apply(number));
        }
    }

}
