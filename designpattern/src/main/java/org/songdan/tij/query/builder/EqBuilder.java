package org.songdan.tij.query.builder;


import org.songdan.tij.query.expression.Equal;
import org.songdan.tij.query.expression.QueryExpression;

/**
 * EqualAdapter Builder
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class EqBuilder implements QueryExpressionBuilder {

	private String field;

	private Object value;

	public EqBuilder(String field, Object value) {
		this.field = field;
		this.value = value;
	}

	@Override
	public QueryExpression build() {
		return new Equal(field,value);
	}
}
