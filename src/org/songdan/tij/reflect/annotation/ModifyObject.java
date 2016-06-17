package org.songdan.tij.reflect.annotation;

import java.lang.annotation.ElementType;
import java.util.Map;

/**
 * 封装运行时修改注解的实体
 * @author Songdan
 * @date 2016/6/16
 */
public class ModifyObject<A> {

    private Class<A> targetAnnotation;

    /**
     * 目标注解
     */
    private String targetAnnotationName;

    /**
     * 目标class对象
     */
    private Class targetClass;

    /**
     * 要修改的class
     */

    private String targetClassName;

    /**
     * 注解的作用范围
     */
    private ElementType type;

    /**
     * 如果是字段上面的注解，class中field的名称
     */
    private String fieldName;

    /**
     * 注解的属性和对应的新值
     * key：属性
     * object:值
     */
    private Map<String, Object> replaceMap;

    public Map<String, Object> getReplaceMap() {
        return replaceMap;
    }

    public void setReplaceMap(Map<String, Object> replaceMap) {
        this.replaceMap = replaceMap;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }





    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }



    public String getTargetAnnotationName() {
        return targetAnnotationName;
    }

    public void setTargetAnnotationName(String targetAnnotationName) {
        this.targetAnnotationName = targetAnnotationName;
    }

    public String getTargetClassName() {
        return targetClassName;
    }

    public void setTargetClassName(String targetClassName) {
        this.targetClassName = targetClassName;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class targetClass) {
        this.targetClass = targetClass;
    }

    public Class<A> getTargetAnnotation() {
        return targetAnnotation;
    }

    public void setTargetAnnotation(Class<A> targetAnnotation) {
        this.targetAnnotation = targetAnnotation;
    }
}
