package org.songdan.tij.enums;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.tuple.Pair;

/**
 * 枚举提取工具类，用于根据枚举字段的值获取枚举
 *
 * @author song dan
 * @since 10 一月 2018
 */
public class EnumExtractorUtils {

	private static ConcurrentHashMap<Pair<Class<? extends Enum>, Class<? extends Annotation>>, EnumAnnotationExtractor> annotationExtractorMap = new ConcurrentHashMap<>();

	private static ConcurrentHashMap<Pair<Class<? extends Enum>, String>, EnumFieldNameExtractor> fieldNameExtractorMap = new ConcurrentHashMap<>();

	private static ConcurrentHashMap<Class<? extends Enum>, EnumNameExtractor> enumNameExtractorMap = new ConcurrentHashMap<>();

	/**
	 * 根据enumClass字段上的annotation来获取匹配给定值的枚举实例
	 *
	 * @param enumClass 枚举class
	 * @param annotationClass 注解class
	 * @param expectValue 匹配的值
	 * @return 满足条件的枚举实例
	 */
	public static Enum byFieldAnnotation(Class<? extends Enum> enumClass, Class<? extends Annotation> annotationClass,
			Object expectValue) {
		return Optional.ofNullable(annotationExtractorMap.get(Pair.of(enumClass, annotationClass))).orElseGet(() -> {
			EnumAnnotationExtractor extractor = new EnumAnnotationExtractor(enumClass, annotationClass);
			annotationExtractorMap.put(Pair.of(enumClass, annotationClass)
					, extractor);
			return extractor;
		}).extract(expectValue).orElse(null);
	}

	/**
	 * 根据enumClass的字段名称来获取匹配给定值的枚举实例
	 *
	 * @param enumClass 枚举class
	 * @param fieldName 字段名称
	 * @param expectValue 匹配的值
	 * @return 满足条件的枚举实例
	 */
	public static Enum byFieldName(Class<? extends Enum> enumClass, String fieldName, Object expectValue) {
		return Optional.ofNullable(fieldNameExtractorMap.get(Pair.of(enumClass, fieldName))).orElseGet(() -> {
			EnumFieldNameExtractor extractor = new EnumFieldNameExtractor(enumClass, fieldName);
			fieldNameExtractorMap.put(Pair.of(enumClass, fieldName), extractor);
			return extractor;
		}).extract(expectValue).orElse(null);
	}

	/**
	 * 根据enum实例名称获取枚举，不区分大小写
	 * @param enumClass 枚举class
	 * @param name 实例名称
	 * @return 满足条件的枚举实例
	 */
	public static Enum byEnumName(Class<? extends Enum> enumClass, String name) {
		return Optional.ofNullable(enumNameExtractorMap.get(enumClass)).orElseGet(() -> {
			EnumNameExtractor extractor = new EnumNameExtractor(enumClass);
			enumNameExtractorMap.put(enumClass, extractor);
			return extractor;
		}).extract(name).orElse(null);
	}

}
