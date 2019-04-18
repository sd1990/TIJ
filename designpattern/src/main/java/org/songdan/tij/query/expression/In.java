package org.songdan.tij.query.expression;

import com.google.common.base.Joiner;
import org.songdan.tij.query.adapter.QueryAdapterFactory;
import org.songdan.tij.query.adapter.QueryExpressionAdapter;

/**
 *
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class In implements QueryExpression {

    private String field;

    private Object[] value;

    public In(String field, Object... value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String getField() {
        return field;
    }

    @Override
    public Object[] getValue() {
        return value;
    }

    @Override
    public String toString() {
        return field + " in (" + Joiner.on(",").join(value) + ")";
    }

}
