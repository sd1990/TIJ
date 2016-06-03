package org.songdan.tij.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyHandler implements InvocationHandler{
    
    private Object proxied;
    
    
    public DynamicProxyHandler(Object realObject) {
        proxied=realObject;
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy);
        return null;
    }

    public static void main(String[] args) {
        Interface proxy = 
                (Interface)Proxy.newProxyInstance(DynamicProxyHandler.class.getClassLoader(), new Class[]{Interface.class}, new DynamicProxyHandler(new RealObject()));
        proxy.dosomething();
    }
}
