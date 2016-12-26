package org.songdan.tij.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyHandler implements InvocationHandler {

    private Object proxied;

    public DynamicProxyHandler(Object realObject) {
        proxied = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(method.getName());
        return method.invoke(proxied, args);
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        RealObject realObject = new RealObject();
        System.out.println(realObject.hashCode());
        Interface proxy = (Interface) Proxy
                .newProxyInstance(realObject.getClass().getClassLoader(), realObject.getClass().getInterfaces(),
                        new DynamicProxyHandler(realObject));
        System.out.println(proxy.hashCode());
        proxy.dosomething();
        proxy.somethingElse("");
    }
}
