package org.songdan.tij.query.builder;


import org.songdan.tij.query.expression.Missing;
import org.songdan.tij.query.expression.QueryExpression;

/**
 * EqualAdapter Builder
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class MissingBuilder implements QueryExpressionBuilder {

	private String field;

	public MissingBuilder(String field) {
		this.field = field;
	}

	@Override
	public QueryExpression build() {
		return new Missing(field);
	}
}
