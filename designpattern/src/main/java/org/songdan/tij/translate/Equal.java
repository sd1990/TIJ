package org.songdan.tij.translate;

import java.util.Objects;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 13 四月 2018
 */
public class Equal implements Expression {

	private String field;

	private String value;

	public Equal(String field, String value) {
		this.field = field;
		this.value = value;
	}

	@Override
	public String translate() {
		if (Objects.isNull(value)) {
			return field + " is null ";
		}
		else {
			return " " + field + " = " + value;
		}
	}
}
