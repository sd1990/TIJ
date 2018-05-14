package org.songdan.tij.query.builder;


import org.songdan.tij.query.expression.NotEqual;
import org.songdan.tij.query.expression.QueryExpression;

/**
 * EqualAdapter Builder
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class NotEqBuilder implements QueryExpressionBuilder {

    private String field;

    private Object value;

    public NotEqBuilder(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public QueryExpression build() {
        return new NotEqual(field, value);
    }
}
