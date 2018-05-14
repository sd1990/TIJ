package org.songdan.tij.query.builder;

import org.songdan.tij.query.expression.ComparisonExpression;
import org.songdan.tij.query.expression.Comparisons;
import org.songdan.tij.query.expression.QueryExpression;

/**
 * Comparesion Builder
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class ComparesionBuilder implements QueryExpressionBuilder {

    private String field;

    private Object value;

    private Comparisons comparison;

    public ComparesionBuilder(String field, Object value, Comparisons comparison) {
        this.field = field;
        this.value = value;
        this.comparison = comparison;
    }

    @Override
    public QueryExpression build() {
		return new ComparisonExpression(field, value, comparison);
    }
}
