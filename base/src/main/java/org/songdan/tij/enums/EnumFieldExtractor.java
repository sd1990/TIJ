package org.songdan.tij.enums;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * 枚举提取器，根据枚举字段提取
 *
 * @author song dan
 * @since 10 一月 2018
 */
public abstract class EnumFieldExtractor extends EnumExtractor {


	public EnumFieldExtractor(Class<? extends Enum> enumClass) {
		super(enumClass);
	}

	protected boolean check(Object expect, Enum item) {
		return expect.equals(Optional.ofNullable(getField()).map(field -> {
			try {
				field.setAccessible(true);
				return field.get(item);
			}
			catch (IllegalAccessException e) {
				return null;
			}
		}).orElse(null));
	}

	protected abstract Field getField();
}
