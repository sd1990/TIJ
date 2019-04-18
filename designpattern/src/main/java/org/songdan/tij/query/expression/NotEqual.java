package org.songdan.tij.query.expression;


import lombok.Getter;
import org.songdan.tij.query.adapter.QueryAdapterFactory;
import org.songdan.tij.query.adapter.QueryExpressionAdapter;

/**
 *
 *
 * @author song dan
 * @since 10 五月 2018
 */
@Getter
public class NotEqual implements QueryExpression {

    private String field;

    private Object value;

    public NotEqual(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return field + "!=" + value;
    }

}
