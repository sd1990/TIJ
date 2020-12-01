package org.songdan.tij.diff;

import com.google.common.collect.Maps;
import com.google.gson.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

/**
 * @author: Songdan
 * @create: 2020-11-22 23:01
 **/
public class JsonDiff {

    public static void main(String[] args) {
        String str1 = "{\"priceFee\":{\"minFee\":\"3\",\"phases\":[{\"beginFee\":\"1.11\",\"endFee\":\"2.1\",\"stepPrice\":\"0\",\"step\":\"0\"},{\"beginFee\":\"2.1\",\"endFee\":\"4.12\",\"stepPrice\":\"2.11\",\"step\":\"1\"},{\"beginFee\":\"4.12\",\"endFee\":\"10\",\"stepPrice\":\"4.4\",\"step\":\"4\"},{\"beginFee\":\"10\",\"endFee\":\"999999999\",\"stepPrice\":\"2\",\"step\":\"3\"}],\"maxFee\":\"18.14\"},\"distanceFee\":{\"minFee\":\"2.34\",\"phases\":[{\"endDistance\":\"100\",\"stepPrice\":\"0\",\"step\":\"0\",\"beginDistance\":\"0\"},{\"endDistance\":\"200\",\"stepPrice\":\"2.34\",\"step\":\"100\",\"beginDistance\":\"100\"},{\"endDistance\":\"400\",\"stepPrice\":\"3.1\",\"step\":\"100\",\"beginDistance\":\"200\"},{\"endDistance\":\"999999999\",\"stepPrice\":\"2\",\"step\":\"100\",\"beginDistance\":\"400\"}]}}";
        String str2 = "{\"distanceFee\":{\"minFee\":2.34,\"phases\":[{\"beginDistance\":0,\"endDistance\":100,\"step\":0,\"stepPrice\":0.0},{\"beginDistance\":100,\"endDistance\":200,\"step\":100,\"stepPrice\":2.34},{\"beginDistance\":200,\"endDistance\":400,\"step\":100,\"stepPrice\":3.1},{\"beginDistance\":400,\"endDistance\":999999999,\"step\":100,\"stepPrice\":2.0}]},\"priceFee\":{\"minFee\":3.0,\"maxFee\":18.14,\"phases\":[{\"beginFee\":1.11,\"endFee\":2.1,\"stepPrice\":0.0,\"step\":0},{\"beginFee\":2.1,\"endFee\":4.12,\"stepPrice\":2.11,\"step\":1},{\"beginFee\":4.12,\"endFee\":10.0,\"stepPrice\":4.4,\"step\":4},{\"beginFee\":10.0,\"endFee\":999999999,\"stepPrice\":2.0,\"step\":3}]}}";
        JsonElement element1 = new JsonParser().parse(str1);
        JsonElement element2 = new JsonParser().parse(str2);
        System.out.println(element1.equals(element2));
        JsonObject obj1 = (JsonObject) new JsonParser().parse(str1);
        JsonObject obj2 = (JsonObject) new JsonParser().parse(str2);
        System.out.println(obj1.equals(obj2));
        System.out.println(obj1.keySet());
        System.out.println(obj1.entrySet());
        Gson gson = new Gson();
        System.out.println(gson.toJson(collectJsonObject(null,obj1,JsonDiff::dealPrimive)));
        System.out.println(gson.toJson(collectJsonObject(null, obj2, JsonDiff::dealPrimive)));
        System.out.println(collectJsonObject(null, obj1, JsonDiff::dealPrimive).equals(collectJsonObject("", obj2, JsonDiff::dealPrimive)));
    }

    private static Map<String, Object> collectJsonObject(String suffix, JsonObject jsonObject,Function<Pair<String,JsonPrimitive>, Object> primitiveFunc) {
        Map<String, Object> map = Maps.newHashMap();
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            String subSuffix = StringUtils.isEmpty(suffix) ? entry.getKey() : suffix + "." + entry.getKey();
            JsonElement element = entry.getValue();
            if (element instanceof JsonObject) {
                map.putAll(collectJsonObject(subSuffix,(JsonObject) element,primitiveFunc));
            } else if (element instanceof JsonArray) {
                map.putAll(collectJsonArray(subSuffix, (JsonArray) element,primitiveFunc));
            } else if(element instanceof JsonNull){
                continue;
            } else if (element instanceof JsonPrimitive) {
                map.put(subSuffix, primitiveFunc.apply(Pair.of(subSuffix,(JsonPrimitive) element)));
            }
        }
        return map;
    }

    private static Object dealPrimive(Pair<String,JsonPrimitive> pair) {
        if (pair.getKey().endsWith("beginTime") || pair.getKey().endsWith("endTime")) {
            return pair.getValue().getAsString();
        }
        if (pair.getKey().endsWith("beginTime") || pair.getKey().endsWith("endTime")) {
            return pair.getValue().getAsString();
        }
        JsonPrimitive element = pair.getRight();
        if (element.isBoolean()) {
            return element.getAsBoolean();
        }
        if (element.isNumber()) {
            return element.getAsString();
        }
        if (element.isString()) {
            return element.getAsString();
        }
        return null;
    }

    private static Map<String, Object> collectJsonArray(String suffix, JsonArray jsonArray,Function<Pair<String,JsonPrimitive>, Object> primitiveFunc) {

        Map<String, Object> map = Maps.newHashMap();
        int i = 0;
        for (JsonElement element : jsonArray) {
            String subSuffix = suffix + "[" + i + "]";
            if (element instanceof JsonObject) {
                map.putAll(collectJsonObject(subSuffix, (JsonObject) element,primitiveFunc));
            } else if (element instanceof JsonArray) {
                map.putAll(collectJsonArray(subSuffix, (JsonArray) element, primitiveFunc));
            } else if (element instanceof JsonNull) {
                continue;
            } else if (element instanceof JsonPrimitive) {
                map.put(subSuffix, primitiveFunc.apply(Pair.of(subSuffix,(JsonPrimitive) element)));
            }
            i++;
        }
        return map;
    }



}
