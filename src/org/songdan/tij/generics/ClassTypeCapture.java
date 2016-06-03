package org.songdan.tij.generics;

import java.util.HashMap;
import java.util.Map;

public class ClassTypeCapture <T>{
    private Class<T> kind;
    
    private Map<String,Class<?>> map=new HashMap<>();
    
    public ClassTypeCapture(Class<T> t) {
        kind=t;
    }
    
    public boolean f(Object arg){
        return kind.isInstance(arg);
    }
    
    public void addType(String typename,Class<?> kind){
        map.put(typename, kind);
    }
    public Object createNew(String typename){
        try {
            return map.get(typename).newInstance();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void main(String[] args) {
        ClassTypeCapture<String> ctt = new ClassTypeCapture<>(String.class);
        ctt.f("hello");
        ctt.addType("zifuchuan", String.class);
        System.out.println(ctt.createNew("zifuchuan"));
    }
}
