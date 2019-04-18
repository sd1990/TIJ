package org.songdan.tij.query.adapter.mysql;

import org.songdan.tij.query.expression.NotEqual;

/**
 * @author: Songdan
 * @create: 2019-04-17 21:21
 **/
public class NotEqualAdapter extends MysqlAdapter {

    private NotEqual notEqual;

    public NotEqualAdapter(NotEqual queryExpression) {
        super(queryExpression);
        this.notEqual = queryExpression;
    }

    @Override
    protected String doDialect(String field, Object value) {
        return field + MysqlConstants.NOT_EQUAL + MysqlToStringBuilder.toString(value);
    }
}
