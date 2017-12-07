package org.songdan.tij.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 07 十二月 2017
 */
public class FieldWrapper {

	private Field field;

	private Object instance;

	public FieldWrapper(Field field,Object instance) {
		this.field = field;
		this.instance = instance;
	}

	public void writeValue(Object value) {
		if (value == null) {
			return;
		}
		Class<?> type = field.getType();
		if (type.isAssignableFrom(value.getClass())) {
			try {
				field.setAccessible(true);
				field.set(instance,value);
			}
			catch (IllegalAccessException e) {
				//DUIYINGD
				try {
					writeMethod().invoke(instance, value);
				}
				catch (IllegalAccessException | InvocationTargetException e1) {
					throw new RuntimeException(e1);
				}
			}
		}
	}

	public Object readValue() {
		try {
			field.setAccessible(true);
			return field.get(instance);
		}
		catch (IllegalAccessException e) {
			try {
				return readMethod().invoke(instance, null);
			}
			catch (IllegalAccessException | InvocationTargetException e1) {
				throw new RuntimeException(e1);
			}
		}
	}

	public boolean isImmutable() {
		return field.getType().isPrimitive() || field.getType().equals(String.class)
				|| (!field.getType().isInterface() && !field.getType().equals(Object.class) ) && field.getType().getSuperclass().equals(Number.class)
				|| field.getType().equals(Boolean.class);
	}

	public Method readMethod() {
		try {
			return instance.getClass().getDeclaredMethod(getGetterName(),null);
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Method writeMethod() {
		try {
			return instance.getClass().getDeclaredMethod(getSetterName(), field.getType());
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getSetterName() {
		return "set" + field.getName().toUpperCase().substring(0, 1) + field.getName().substring(1);
	}

	public String getGetterName() {
		return "get" + field.getName().toUpperCase().substring(0, 1) + field.getName().substring(1);
	}

}
