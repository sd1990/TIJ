package org.songdan.tij.enums;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Objects;

/**
 * 枚举提取器,根据字段上的注解注解提取
 *
 * @author song dan
 * @since 10 一月 2018
 */
public class EnumAnnotationExtractor extends EnumFieldExtractor {

	private Class<? extends Annotation> targetAnnotation;

	public EnumAnnotationExtractor(Class<? extends Enum> enumClass,Class<? extends Annotation> targetAnnotation) {
		super(enumClass);
		this.targetAnnotation = targetAnnotation;
	}

	protected Field getField(){
		Field[] declaredFields = getEnumClass().getDeclaredFields();
		for (Field field : declaredFields) {
			Annotation annotation = field.getDeclaredAnnotation(targetAnnotation);
			if (Objects.nonNull(annotation)) {
				return field;
			}
		}
		return null;
	}

}
