package org.songdan.tij.enums;

import java.lang.reflect.Field;

/**
 * 枚举提取器，根据字段名称提取
 *
 * @author song dan
 * @since 10 一月 2018
 */
public class EnumFieldNameExtractor extends EnumFieldExtractor {


	private String fieldName;

	public EnumFieldNameExtractor(Class<? extends Enum> enumClass, String fieldName) {
		super(enumClass);
		this.fieldName = fieldName;
	}


	protected Field getField() {
		Field[] declaredFields = getEnumClass().getDeclaredFields();
		for (Field field : declaredFields) {
			if (field.getName().equals(fieldName)) {
				return field;
			}
		}
		return null;
	}

}
