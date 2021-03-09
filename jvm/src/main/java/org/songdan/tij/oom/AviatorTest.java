package org.songdan.tij.oom;

import com.alibaba.fastjson.JSONPath;
import com.google.gson.*;
import com.google.gson.internal.LazilyParsedNumber;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.*;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.spi.json.GsonJsonProvider;
import sun.misc.Regexp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * @author: Songdan
 * @create: 2019-04-12 19:45
 **/
public class AviatorTest {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().create();
        AviatorEvaluator.addFunction(new AbstractFunction() {

            Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider())
                    .options(Option.ALWAYS_RETURN_LIST, Option.SUPPRESS_EXCEPTIONS).build();

            @Override
            public String getName() {
                return "numPath";
            }

            @Override
            public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2,AviatorObject arg3) {
                String content = (String) arg1.getValue(env);
                String path = (String) arg2.getValue(env);
                String expression = (String) arg3.getValue(env);
                JsonArray objArrJ = JsonPath.using(conf).parse(content).read(path);
                if (objArrJ.size() <= 0) {
                    return AviatorBoolean.FALSE;
                }
                Expression compile = AviatorEvaluator.compile(expression, true);
                boolean res = true;
                for (JsonElement jsonElement : objArrJ) {
                    Number v = jsonElement.getAsNumber();
                    v = v.doubleValue();
                    Map<String, Object> innerMap = new HashMap<>();
                    innerMap.put("num", v);
                    res &= (boolean) compile.execute(innerMap);
                }
                return AviatorBoolean.valueOf(res);
            }
        });
        AviatorEvaluator.addFunction(new AbstractFunction() {

            Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider())
                    .options(Option.ALWAYS_RETURN_LIST, Option.SUPPRESS_EXCEPTIONS).build();

            @Override
            public String getName() {
                return "arrayLength";
            }

            @Override
            public AviatorLong call(Map<String, Object> env, AviatorObject arg1) {
                try {
                    String content = (String) arg1.getValue(env);
                    JsonParser jsonParser = new JsonParser();
                    JsonElement jsonElement = jsonParser.parse(content);
                    if (jsonElement.isJsonArray()) {
                        JsonArray array = jsonElement.getAsJsonArray();
                        return new AviatorLong(array.size());
                    }
                    return new AviatorLong(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new AviatorLong(0);
                }
            }
        });
//        Expression expression = AviatorEvaluator.compile("numPath(context.loss_rate,'$.normalPayPercent','0<num && num<100')");
//        Expression expression = AviatorEvaluator.compile("str==nil && str2==''");
//        Expression expression = AviatorEvaluator.compile("arrayLength(context.arr)==1");
        Expression expression = AviatorEvaluator.compile("context.discount_cls=~/\\[[123](\\s?,\\s?[123])*\\]/");
//        Expression expression = AviatorEvaluator.compile("list = [1,2,3,4]include([1,2,3,4],thirdCityId)");
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> innerMap = new HashMap<>();
        map.put("context", innerMap);
        map.put("str", null);
        map.put("str2", "");
        map.put("num", 1);
        map.put("thirdCityId", 1);
        innerMap.put("loss_rate", "{\"normalPayPercent\":null,\"specialPayPercent\":null}");
        innerMap.put("arr", "[{\"normalPayPercent\":null,\"specialPayPercent\":null}]");
        innerMap.put("discount_cls", "[1 , 2,3]");
        System.out.println(expression.execute(map));
//        System.out.println(AviatorEvaluator.execute("include(seq.list(1,2),1)"));

        Pattern pattern = Pattern.compile("\\[[123](\\s?,\\s?[123])*\\]");
        System.out.println(pattern.matcher("[1]").matches());
        System.out.println(pattern.matcher("[1,2]").matches());
        System.out.println(pattern.matcher("[1,2,3]").matches());
        expression = AviatorEvaluator.compile("!string.contains(context.discount_cls,'1')||context.fee_mode!=4||context.tech_discount_rate>0");
        map = new HashMap<>();
        innerMap = new HashMap<>();
        map.put("context", innerMap);
        innerMap.put("fee_mode", 4);
        innerMap.put("discount_cls", "[1 , 2,3]");
        innerMap.put("wm_poi_logistics_id", 103);
        System.out.println(expression.execute(map));
    }



    public static BigDecimal getJsonStringForPath(String strJson, String strJPath) {
        Configuration conf = Configuration.builder().jsonProvider(new GsonJsonProvider())
                .options(Option.ALWAYS_RETURN_LIST, Option.SUPPRESS_EXCEPTIONS).build();

        JsonArray objArrJ = JsonPath.using(conf).parse(strJson).read(strJPath);
        return objArrJ.get(0).getAsBigDecimal();
    }

    public static void test() {
        AviatorEvaluator.addFunction(new TestFunction());
        Expression compile = AviatorEvaluator.compile("test(name,1)");
        Map<String, Object> map = new HashMap<>();
        map.put("name", "songdan");
        compile.execute(map);
        Expression expression = AviatorEvaluator.compile("name>1");
        map.put("name", 2);
        Object result = expression.execute(map);
        System.out.println(result);

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

    public static class ObjA{

        private String name;

        private ObjB b;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ObjB getB() {
            return b;
        }

        public void setB(ObjB b) {
            this.b = b;
        }
    }


    public static class ObjB{

        private Integer num;

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }
    }


}
