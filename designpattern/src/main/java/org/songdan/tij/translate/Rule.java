package org.songdan.tij.translate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 13 四月 2018
 */
@JsonDeserialize(as = Rule.class)
public class Rule extends AbsRule {

    private String field;

    private String op;

    private String value;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Expression toExpression() {
        Expression expression;
        switch (op) {
        case "equal":
            expression = ExpressionBuilder.equal(field, value);
            break;
        default:
            throw new IllegalArgumentException();
        }
        return expression;
    }
}
