package org.songdan.tij.file.util;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 04 十二月 2017
 */
public enum EqumentType {


	NORAML_TYPE(1, "常温类型"),
	COLD_TYPE(2, "冷柜类型"),
	DESK_TYPE(4, "冷柜类型"),
	PLUG_TYPE(5, "冷柜类型"),
	HEAT_TYPE(3, "热柜类型");

	private int code;

	private String desc;

	EqumentType(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}


}
