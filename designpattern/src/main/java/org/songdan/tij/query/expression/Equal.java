package org.songdan.tij.query.expression;


import org.songdan.tij.query.QueryExpression;
import org.songdan.tij.translate.Expression;
import org.songdan.tij.translate.ExpressionBuilder;

/**
 *
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class Equal implements QueryExpression {

    private String field;

    private Object value;

    public Equal(String field, Object value) {
        this.field = field;
        this.value = value;
    }

    @Override
    public Expression translate() {
        if (value instanceof Boolean) {
            return ExpressionBuilder.equal(field, (Boolean) value);
        } else if (value instanceof String) {
            return ExpressionBuilder.equal(field, (String) value);
        } else if (value instanceof Number) {
            return ExpressionBuilder.equal(field, (Number) value);
        } else {
            // todo error MSG
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return field + "=" + value;
    }
}
