package org.songdan.tij.query.builder;

import java.util.stream.Collectors;

import org.songdan.tij.query.QueryExpression;
import org.songdan.tij.query.expression.CombinationExpression;
import org.songdan.tij.query.expression.Relation;

/**
 * Or Relation Builder
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class OrBuilder extends CombinationBuilder {

    public OrBuilder(CombinationBuilder andBuilder) {
        super(andBuilder);
    }

    @Override
    public QueryExpression build() {
        return new CombinationExpression(
                getBuilders().stream().map(QueryExpressionBuilder::build).collect(Collectors.toList()), Relation.OR);
    }
}
