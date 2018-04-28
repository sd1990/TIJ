package org.songdan.tij.translate;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 13 四月 2018
 */
public class True implements Expression {

	public final static True TRUE = new True();

	private True() {

	}

	@Override
	public String translate() {
		return " 1 = 1 ";
	}
}
