package org.songdan.tij.reflect.annotation;

import javax.xml.bind.annotation.XmlRootElement;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * 运行时修改注解的值
 * @author Songdan
 * @date 2016/6/16
 */
public class AnnotationRuntimeModify {

    /**
     * 此方法不够通用，但是也可用，1.7只能替换类上的注解
     * @param modifyObject
     * @return
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    public static boolean modify(final ModifyObject modifyObject)
            throws NoSuchFieldException, IllegalAccessException {
        Class<? extends Object> objClass = modifyObject.getTargetClass();
        final XmlRootElement annotation = objClass.getAnnotation(XmlRootElement.class);
        final Annotation newAnnotation = new XmlRootElement() {

            @Override
            public String namespace() {
                return annotation.namespace();
            }

            @Override
            public String name() {
                return "ABCD";
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                return annotation.annotationType();
            }

        };
        Field field = Class.class.getDeclaredField("annotations");
        field.setAccessible(true);
        Map<Class<? extends Annotation>, Annotation> annotations = (Map<Class<? extends Annotation>, Annotation>) field.get(objClass);
        annotations.put(XmlRootElement.class, newAnnotation);
        return true;
    }

    /**
     * 运行时修改指定类上注解的属性,这个是可以通用的，但是不支持字段和类上的注解
     * @param modifyObject
     * @param <A>
     */
    public static <A extends Annotation> void modifyRuntime(ModifyObject<A> modifyObject) {


        ElementType type = modifyObject.getType();
        Class<?> targetClass = modifyObject.getTargetClass();
        Class<A> targetAnnotation = modifyObject.getTargetAnnotation();
        A annotation = null;
        switch (type) {
            case TYPE:
                annotation = targetClass.getAnnotation(targetAnnotation);
                InvocationHandler handler = Proxy.getInvocationHandler(annotation);
                Field f;
                try {
                    f = handler.getClass().getDeclaredField("memberValues");
                } catch (NoSuchFieldException | SecurityException e) {
                    throw new IllegalStateException(e);
                }
                f.setAccessible(true);
                Map<String, Object> memberValues;
                Map<String, Object> replaceMap = modifyObject.getReplaceMap();
                try {
                    memberValues = (Map<String, Object>) f.get(handler);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
                for (Map.Entry<String,Object> entry : replaceMap.entrySet()) {
                    Object oldValue = memberValues.get(entry.getKey());
                    Object newValue = entry.getValue();
                    if (oldValue == null || oldValue.getClass() != newValue.getClass()) {
                        throw new IllegalArgumentException();
                    }
                    memberValues.put(entry.getKey(),newValue);
                }
                break;
            case FIELD:
                break;
            case METHOD:
                break;
            default:
                break;
        }
    }


    public static Annotation newAnnotation(Annotation annotation,Class<? extends Annotation> type,String attrName,Object newValue) {
        AnnotationInvocationHandler invocationHandler = new AnnotationInvocationHandler(annotation,attrName,newValue);
        return (Annotation)Proxy.newProxyInstance(annotation.getClass().getClassLoader(), new Class[] { type }, invocationHandler);
    }

    public static void main(String[] args) {
       /* XmlRootElement annotation = RequestParam.class.getAnnotation(XmlRootElement.class);
        XmlRootElement anno =
                (XmlRootElement) newAnnotation(annotation, XmlRootElement.class, "name", "test_name");
        System.out.println(anno.name());*/
    }
}

