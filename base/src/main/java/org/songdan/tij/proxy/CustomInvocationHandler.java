package org.songdan.tij.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 03 三月 2018
 */
public class CustomInvocationHandler implements InvocationHandler {

	private	Subject realSubject;

	public CustomInvocationHandler(Subject realSubject) {
		this.realSubject = realSubject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("ObjectProxy execute:" + method.getName());
		System.out.println("first argument class is : "+proxy.getClass().getName());
		//此处如果调用proxy将会出现调用死循环
		return method.invoke(realSubject,args);
	}

	public static void main(String[] args) {
		Subject proxyInstance = (Subject) Proxy.newProxyInstance(Subject.class.getClassLoader(), new Class[] {Subject.class}, new CustomInvocationHandler(new RealSubject()));
		Class<? extends Subject> proxyInstanceClass = proxyInstance.getClass();
		System.out.println(proxyInstanceClass.getName());
		int modifiers = proxyInstanceClass.getModifiers();
		System.out.println(Modifier.isFinal(modifiers));
	}

	static class RealSubject implements Subject{
		public void name() {
			System.out.println("hello subject");
		}
	}

	interface Subject{
		void name();
	}
}
