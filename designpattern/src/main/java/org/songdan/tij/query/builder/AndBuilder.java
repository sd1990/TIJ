package org.songdan.tij.query.builder;

import java.util.stream.Collectors;

import org.songdan.tij.query.QueryExpression;
import org.songdan.tij.query.expression.CombinationExpression;
import org.songdan.tij.query.expression.Relation;


/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class AndBuilder extends CombinationBuilder {

	public AndBuilder(CombinationBuilder combinationBuilder) {
		super(combinationBuilder);
	}

    @Override
    public QueryExpression build() {
        return new CombinationExpression(
                getBuilders().stream().map(QueryExpressionBuilder::build).collect(Collectors.toList()), Relation.AND);
    }
}
