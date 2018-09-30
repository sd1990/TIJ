package org.songdan.tij.atomic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

/**
 * 反射工具类
 *
 * @author: Songdan
 * @create: 2018-09-28 18:05
 **/
public final class ReflectionUtil {

    private static final Counter counter = new Counter(new ThresholdClearStrategy(10L));

    private static class ConstructorKey<T> {
        private Class<? extends T> cls;

        private Class[]            paramType;

        public ConstructorKey(Class<? extends T> cls, Class[] paramType) {
            this.cls = cls;
            this.paramType = paramType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            ConstructorKey that = (ConstructorKey) o;
            return Objects.equals(cls, that.cls) && Arrays.equals(paramType, that.paramType);
        }

        @Override
        public int hashCode() {

            int result = Objects.hash(cls);
            result = 31 * result + Arrays.hashCode(paramType);
            return result;
        }

    }

    /**
     * 通过反射创建实例
     * <note>如果类中存在参数列表相同的构造函数（差异在于基本类型和包装类型），请不要使用这个方法！！！</note>
     * @param tClass
     * @param args 参数列表
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<? extends T> tClass, Object... args) {
        try {
            Constructor<? extends T> constructor = getConstructor(tClass, args);
            if (constructor == null) {
                throw new IllegalArgumentException(tClass + "根据参数:[" + (args) + "]找不到对应的构造函数");
            }
            if (!constructor.isAccessible()) {
                constructor.setAccessible(true);
            }
            return constructor.newInstance(args);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | ExecutionException e) {
            throw new IllegalArgumentException("通过反射构造对象:" + tClass + "失败", e);
        }
    }

    private static <T> Constructor<? extends T> getConstructor(Class<? extends T> tClass, Object[] args) throws ExecutionException {
        Class<?>[] paramTypes = getParamTypes(args);
        return getConstructor(tClass, paramTypes);
    }

    private static Class<?>[] getParamTypes(Object[] args) {
        Class<?>[] paramTypes;
        if (args == null || args.length == 0) {
            paramTypes = new Class<?>[0];
        } else {
            paramTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i].getClass();
            }
        }
        return paramTypes;
    }

    private static <T> Constructor<? extends T> getConstructor(Class<? extends T> tClass, Class<?>[] paramTypes) {
        Constructor<? extends T> constructor = null;
        Constructor<?>[] declaredConstructors = tClass.getDeclaredConstructors();
        for (int i = 0; i < declaredConstructors.length; i++) {
            Class<?>[] parameterTypes = declaredConstructors[i].getParameterTypes();
            if (paramTypes.length != parameterTypes.length) {
                continue;
            }
            boolean match = true;
            for (int j = 0; j < parameterTypes.length; j++) {
                Class<?> needType = parameterTypes[j];
                Class<?> getType = paramTypes[j];
                if (needType != getType) {
                    //继承关系
                    if (needType.isAssignableFrom(getType)) {
                        continue;
                    }
                    //包装类型
                    if (needType.isPrimitive()) {
                        match = choicePrimitive(getType).equals(needType);
                    } else {
                        match = false;
                    }
                    if (!match) {
                        //下一个方法
                        break;
                    }
                }
            }
            if (match) {
                constructor = (Constructor<? extends T>) declaredConstructors[i];
                break;
            }
        }
        return constructor;
    }

    private static Class<?> choicePrimitive(Class c) {
        if (Double.class == c) {
            return double.class;
        }
        if (Float.class == c) {
            return float.class;
        }
        if (Character.class == c) {
            return char.class;
        }
        if (Integer.class == c) {
            return int.class;
        }
        if (Long.class == c) {
            return long.class;
        }
        if (Boolean.class == c) {
            return boolean.class;
        }
        if (Short.class == c) {
            return short.class;
        }
        return c;
    }

}
