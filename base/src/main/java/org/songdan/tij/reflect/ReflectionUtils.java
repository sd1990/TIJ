package org.songdan.tij.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import org.songdan.tij.model.Person;

/**
 * 反射工具类
 *
 * @author song dan
 * @since 07 十二月 2017
 */
public class ReflectionUtils {

    public static <T> Constructor<? extends T> getDefaultConstructor(Class<? extends T> c) {
        Constructor<? extends T> defaultConstructor = null;
        try {
            defaultConstructor = c.getDeclaredConstructor(null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return defaultConstructor;
    }

    public static <T> T getInstance(Class<? extends T> c) {
        try {
            return getDefaultConstructor(c).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("范型强转")
    public static <T> T deepCopy(T t) {
    	if(t == null){
			return t;
		}
        Class<T> aClass = (Class<T>) t.getClass();
        T newInstance = getInstance(aClass);
        Field[] declaredFields = aClass.getDeclaredFields();
		for (Field field : declaredFields) {
			FieldWrapper source = new FieldWrapper(field, t);
			FieldWrapper target = new FieldWrapper(field, newInstance);
			if(source.isImmutable()){
				target.writeValue(source.readValue());
			}else{
				Object value = source.readValue();
				if(value == t){
					//防止自己引用自己
					target.writeValue(value);
				}else{
					target.writeValue(DeepCopyUtils.deepCopy(value));
				}
			}
        }
        return newInstance;
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setName("songdan");
        person.setAge(27);
		System.out.println();
		Person newPerson = deepCopy(person);
        System.out.println(person == newPerson);
        System.out.println(person.equals(newPerson));
    }

}
