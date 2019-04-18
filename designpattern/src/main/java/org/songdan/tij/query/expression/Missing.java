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
public class Missing implements QueryExpression {

    private String field;

    public Missing(String field) {
        this.field = field;
    }

    @Override
    public Object getValue() {
        return null;
    }

    @Override
    public String toString() {
        return field + " is null ";
    }

}
