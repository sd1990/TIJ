package org.songdan.tij.query.adapter.mysql;

import org.songdan.tij.query.expression.QueryExpression;

/**
 * @author: Songdan
 * @create: 2019-04-17 21:25
 **/
public class MissingAdapter extends MysqlAdapter {

    public MissingAdapter(QueryExpression queryExpression) {
        super(queryExpression);
    }

    @Override
    protected String doDialect(String field, Object value) {
        return field + MysqlConstants.IS_NULL;
    }
}
