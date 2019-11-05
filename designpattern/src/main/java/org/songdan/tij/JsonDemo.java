package org.songdan.tij;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

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

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        A a = new A();
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

        }
    }

}
