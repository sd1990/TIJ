package org.songdan.tij.holding;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author: Songdan
 * @create: 2020-06-27 11:57
 **/
public class PathMap {

    private Map<String, Object> map = Maps.newHashMap();

    public PathMap(Map<String, Object> map) {
        this.map = map;
    }


    public Map<String, Object> tiePath() {
        Map<String, Object> result = Maps.newHashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            tiePath0(key, value, result);
        }
        return result;
    }

    public void tiePath0(String path, Object value, Map<String, Object> result) {
        Class<?> c = value.getClass();
        if (List.class.isAssignableFrom(c)) {
            List<?> list = (List<?>) value;
            for (int i = 0; i < list.size(); i++) {
                tiePath0(path + "[" + i + "]", list.get(i), result);
            }
        } else if (Map.class.isAssignableFrom(c)) {
            Map innerMap = (Map) value;
            Set<Map.Entry> set = innerMap.entrySet();
            Iterator<Map.Entry> iterator = set.iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = iterator.next();
                tiePath0(path + "." + entry.getKey(), entry.getValue(), result);
            }
        } else if (c.isArray()) {
            Object[] arr = (Object[]) value;
            for (int i = 0; i < arr.length; i++) {
                tiePath0(path + "[" + i + "]", arr[i], result);
            }
        } else if (c.isPrimitive() || isPrimitiveBox(c)) {
            result.put(path, value);
        } else if (!c.getName().startsWith("java")) {
            //利用反射
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    tiePath0(path + "." + field.getName(), field.get(value), result);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * java.lang.Boolean
     * java.lang.Character
     * java.lang.Byte
     * java.lang.Short
     * java.lang.Integer
     * java.lang.Long
     * java.lang.Float
     * java.lang.Double
     * java.lang.Void
     * java.lang.String
     *
     * @param c
     * @return
     */
    public boolean isPrimitiveBox(Class<?> c) {
        return Boolean.class.equals(c)
                || Character.class.equals(c)
                || Byte.class.equals(c)
                || Short.class.equals(c)
                || Integer.class.equals(c)
                || Long.class.equals(c)
                || Float.class.equals(c)
                || Double.class.equals(c)
                || String.class.equals(c);
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        data.put("a", 123);
        data.put("b", "1,2");
        data.put("c", "{'d':20}");
        data.put("list", Lists.newArrayList(1, 2, 3, 4, 5));
        data.put("demo", new Demo("coco", 100L));
        map.put("context", data);
        Demo demo = new Demo("xjj", 12345678L);
        Obj obj = new Obj();
        obj.setDemo(demo);
        demo.setObj(obj);
        map.put("obj", obj);
        Map<String, Object> tiePath = new PathMap(map).tiePath();
        System.out.println(tiePath);
    }

    public static class Demo {
        private String name;

        private Long age;

        private Obj obj;

        public Demo(String name, Long age) {
            this.name = name;
            this.age = age;
        }

        public Obj getObj() {
            return obj;
        }

        public void setObj(Obj obj) {
            this.obj = obj;
        }
    }

    public static class Obj {
        private Demo demo;

        public Obj() {
        }

        public Demo getDemo() {
            return demo;
        }

        public void setDemo(Demo demo) {
            this.demo = demo;
        }
    }
}
