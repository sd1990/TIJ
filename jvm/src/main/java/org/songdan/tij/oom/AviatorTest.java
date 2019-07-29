package org.songdan.tij.oom;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorBoolean;
import com.googlecode.aviator.runtime.type.AviatorObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Songdan
 * @create: 2019-04-12 19:45
 **/
public class AviatorTest {

    public static void main(String[] args) {
//        for (int i = 0; i < Long.MAX_VALUE; i++) {
//            String expression = "(a-(b-c)*5)>10";
//            Expression compile = AviatorEvaluator.compile(expression);
//            Map<String, Object> env = new HashMap<>();
//            env.put("a", 20);
//            env.put("b", 2);
//            env.put("c", 0);
//            System.out.println(compile.execute(env));
//        }
        test();
    }

    public static void test() {
        AviatorEvaluator.addFunction(new TestFunction());
        Expression compile = AviatorEvaluator.compile("test(name,1)");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "songdan");
        compile.execute(map);
//        AviatorEvaluator.exec("test(\"abc\",1)");
    }

    static class TestFunction extends AbstractFunction {

        @Override
        public AviatorObject call(Map<String, Object> env,AviatorObject arg1,AviatorObject arg2) {
            System.out.println(FunctionUtils.getStringValue(arg1, env));
            System.out.println(FunctionUtils.getNumberValue(arg2, env));
            return AviatorBoolean.TRUE;
        }

        @Override
        public String getName() {
            return "test";
        }
    }


}
