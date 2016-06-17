package org.songdan.tij.reflect.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 使用动态代理技术,替换注解
 * @author Songdan
 * @date 2016/6/16
 */
public class AnnotationInvocationHandler implements InvocationHandler {

    private Annotation origin;

    private String attrName;

    private Object newValue;

    public AnnotationInvocationHandler(Annotation origin, String attrName, Object newValue) {
        this.attrName = attrName;
        this.newValue = newValue;
        this.origin = origin;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getName().equals(attrName)) {
            return newValue;
        }
        else {
            Class<?>[] classes = toClassArray(args);
            return origin.getClass().getDeclaredMethod(method.getName(), classes).invoke(origin, args);
        }
    }

    private static Class<?>[] toClassArray(Object[] args) {
        if (args==null||args.length==0) {
            return null;
        }
        Class<?>[] classArr = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            classArr[i] = args[i].getClass();
        }
        return classArr;
    }
}
