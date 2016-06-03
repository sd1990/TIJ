package org.songdan.tij.generics;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContainerMethodDifferences {
    static Set<String> methodSet(Class<?> type){
        System.out.println("method set in :"+type.getName());
        Set<String> set =new HashSet<>();
        for (Method method : type.getMethods()) {
            set.add(method.getName());
        }
        return set;
    }
    static List<String> interfaces(Class<?> type){
        System.out.println("interfaces in :"+type.getName());
        List<String> list = new ArrayList<>();
        for (Class<?> in : type.getInterfaces()) {
            list.add(in.getName());
        }
        System.out.println(list);
        return list;
    }
    static Set<String> object = methodSet(Object.class);
}
 