package org.songdan.tij.oom;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: Songdan
 * @create: 2019-04-12 19:45
 **/
public class AviatorTest {

    public static void main(String[] args) {
        for (int i = 0; i < Long.MAX_VALUE; i++) {
            String expression = "(a-(b-c)*5)>10";
            Expression compile = AviatorEvaluator.compile(expression);
            Map<String, Object> env = new HashMap<>();
            env.put("a", 20);
            env.put("b", 2);
            env.put("c", 0);
            System.out.println(compile.execute(env));
        }
    }

}
