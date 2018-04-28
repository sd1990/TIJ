package org.songdan.tij.translate;

import java.util.stream.Stream;

/**
 * 表达式Builder
 *
 * @author song dan
 * @since 13 四月 2018
 */
public class ExpressionBuilder {

	public static Expression equal(String field, String value) {
		return new Equal(field,value);
	}

	public static Expression and(Expression... expressions) {
		return combination(Relation.AND, expressions);
	}

	public static Expression or(Expression... expressions) {
		return combination(Relation.OR, expressions);
	}

	private static Expression combination(Relation relation, Expression... expressions) {
		return Stream.of(expressions).reduce(new Combination(relation), (prev, cur) -> {
			Combination c = (Combination) prev;
			return c.add(cur);
		});
	}

}
