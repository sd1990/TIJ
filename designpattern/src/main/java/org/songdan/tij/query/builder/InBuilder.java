package org.songdan.tij.query.builder;

import java.util.Collection;


import com.google.common.collect.Lists;
import org.songdan.tij.query.expression.In;
import org.songdan.tij.query.expression.QueryExpression;

/**
 *
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class InBuilder implements QueryExpressionBuilder {

    private String field;

    private Object[] value;

    public InBuilder(String field, Object[] value) {
        this.field = field;
        this.value = value;
    }

    public InBuilder(String field, Collection<Object> value) {
        this.field = field;
        this.value = value.toArray();
    }

    @Override
    public QueryExpression build() {
        return new In(field, value);
    }
}
