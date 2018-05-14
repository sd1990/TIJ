package org.songdan.tij.query.builder;


import org.songdan.tij.query.expression.Like;
import org.songdan.tij.query.expression.QueryExpression;

/**
 * EqualAdapter Builder
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class LikeBuilder implements QueryExpressionBuilder {

    private String field;

    private String value;

    public LikeBuilder(String field, String value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public QueryExpression build() {
		return new Like(field, value);
    }
}
