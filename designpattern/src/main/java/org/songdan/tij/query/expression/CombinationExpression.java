package org.songdan.tij.query.expression;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import org.songdan.tij.query.QueryExpression;
import org.songdan.tij.translate.Expression;
import org.songdan.tij.translate.ExpressionBuilder;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class CombinationExpression implements QueryExpression {

	private List<QueryExpression> expressionList;

	private Relation relation;

	public CombinationExpression(List<QueryExpression> expressionList, Relation relation) {
		this.expressionList = expressionList;
		this.relation = relation;
	}

	@Override
	public Expression translate() {
		if (Relation.AND.equals(relation)) {
			return ExpressionBuilder.and(expressionList.stream().map(QueryExpression::translate).collect(Collectors.toList())
					.toArray(new Expression[expressionList.size()]));
		} else if (Relation.OR.equals(relation)) {
			return ExpressionBuilder.or(expressionList.stream().map(QueryExpression::translate).collect(Collectors.toList())
					.toArray(new Expression[expressionList.size()]));
		}
		return null;
	}

	@Override
	public String toString() {
		return "("+Joiner.on(relation.getSymbol()).join(expressionList)+")";
	}
}
