package org.songdan.tij.anno;

import java.lang.reflect.Method;

/**
 * @author: Songdan
 * @create: 2018-11-18 20:17
 **/
public class StepDemo {

    @Step
    public void test() {

    }

    public static void main(String[] args) throws NoSuchMethodException {
        StepDemo stepDemo = new StepDemo();
        Method test = stepDemo.getClass().getMethod("test");
        Step annotation = test.getAnnotation(Step.class);
        System.out.println(annotation.annotationType());
        Parser parser = annotation.annotationType().getAnnotation(Parser.class);
        System.out.println(parser);
    }

}
