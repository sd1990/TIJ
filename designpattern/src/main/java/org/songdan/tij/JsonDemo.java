package org.songdan.tij;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.Deserializers;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.type.MapType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.Data;
import org.songdan.tij.designpattern.composite.JsonUtil;
import org.songdan.tij.translate.AbsRule;
import org.songdan.tij.translate.CombinationRule;
import org.songdan.tij.translate.Rule;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Songdan
 * @create: 2019-09-27 17:54
 **/
public class JsonDemo {

    public static class A {
        private String a;
        private String b;
        private String c;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }
    }

    @Data
    public static class Demo{
        private String name;

        public String getName() {
            return name;
        }
    }

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        /*A a = new A();
        a.setA("aaaa");
        String s = objectMapper.writer().writeValueAsString(a);
        System.out.println(s);
        try {
            objectMapper.readValue("{\"a\":\"aaaa\",\"b\":null,\"c\":null,\"d\":\"ddd\"}", A.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {

            A a1 = JSON.parseObject("{\"a\":\"aaaa\",\"b\":null,\"c\":null,\"d\":\"ddd\"}", A.class);
            System.out.println(a1.a);
        } catch (Exception e) {

        }*/
//        String str = "{\"S\":\"19\",\"A\":\"15\"}";
//        Map<String, BigDecimal> result = JsonUtil.ofMap(str, String.class, BigDecimal.class);
//        String str2 = "{\"amount\":\"5\",\"sdRate\":\"-1\",\"shopSdRates\":\"{\\\"S\\\":\\\"10\\\",\\\"A\\\":\\\"9\\\",\\\"B1\\\":\\\"9\\\",\\\"B2\\\":\\\"8\\\",\\\"C\\\":\\\"7\\\",\\\"NONE\\\":\\\"6\\\"}\",\"techFee\":\"10\",\"lowerLimit\":\"-1\",\"upperLimit\":\"-1\"}";
//        Fee fee = JsonUtil.of(str2, Fee.class);
//        System.out.println(fee);
//        Map<String, BigDecimal> map = new HashMap<>();
//        map.put("S", BigDecimal.valueOf(19));
//        System.out.println(objectMapper.writer().writeValueAsString(map));
//        System.out.println(objectMapper.writer().writeValueAsString(str));
//        FeeSettingEditRequest request = objectMapper.readValue("{\"setting\": {\"amount\": \"5\",\"sdRate\": \"-1\",\"shopSdRates\": \"{\\\"S\\\":\\\"19\\\",\\\"A\\\":\\\"15\\\"}\",\"techFee\": \"10\",\"lowerLimit\": \"-1\",\"upperLimit\": \"-1\"},\"settingId\": \"123\",\"settingBiz\": 4,\"settingScope\": 1}", FeeSettingEditRequest.class);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        objectMapper.setVisibility(objectMapper.getSerializationConfig()
                .getDefaultVisibilityChecker()
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE));
//                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
//                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
//                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE));

        Demo demo = new Demo();
        demo.setName("abc");
        ObjectNode jsonNode = new ObjectNode(JsonNodeFactory.instance);
        jsonNode.put("name", "jsonNode");
        POJO pojo = new POJO();
        pojo.setName("pojo");
        pojo.age = 10;
        System.out.println(objectMapper.writeValueAsString(jsonNode));
        System.out.println(objectMapper.writeValueAsString(pojo));
        System.out.println(objectMapper.writeValueAsString(demo));
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Result<Long> result = new Result<>();
        result.setValue(1001L);
        String json = gson.toJson(result);
        TypeToken<Result<Long>> typeToken = new TypeToken<Result<Long>>(){

        };
        Result<Long> r = gson.fromJson(json, typeToken.getType());
        System.out.println(r.getValue());
    }



    @Data
    public static class Fee{
        @JsonDeserialize(using = String2MapDeserialize.class)
        Map<String,BigDecimal> shopSdRates;
    }

    public static class String2MapDeserialize extends JsonDeserializer<Map<String,BigDecimal>> {

        @Override
        public Map<String,BigDecimal> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            ObjectMapper objectMapper = (ObjectMapper) jsonParser.getCodec();
            MapType mapType = objectMapper.getTypeFactory().constructMapType(HashMap.class, String.class, BigDecimal.class);
            return objectMapper.readValue(jsonParser.getText(),mapType);
        }
    }

}

class Result<T>{
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
