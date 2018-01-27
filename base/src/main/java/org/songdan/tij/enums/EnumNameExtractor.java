package org.songdan.tij.enums;

/**
 * 根据实例名称获取，不区分大小写
 *
 * @author song dan
 * @since 10 一月 2018
 */
public class EnumNameExtractor extends EnumExtractor {

	public EnumNameExtractor(Class<? extends Enum> enumClass) {
		super(enumClass);
	}

	protected boolean check(Object expect, Enum item) {
		return item.name().toUpperCase().equals(expect.toString().toUpperCase());
	}

}
