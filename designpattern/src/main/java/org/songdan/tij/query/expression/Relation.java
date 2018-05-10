package org.songdan.tij.query.expression;

/**
 *
 *
 * @author song dan
 * @since 13 四月 2018
 */
public enum Relation {
	AND("&&"), OR("||");

	private String symbol;

	Relation(String symbol) {
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
}
