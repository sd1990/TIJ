package org.songdan.tij.query.adapter.mysql;

import org.songdan.tij.query.adapter.QueryExpressionAdapter;
import org.songdan.tij.query.expression.QueryExpression;

/**
 * @author: Songdan
 * @create: 2019-04-17 20:29
 **/
public abstract class MysqlAdapter implements QueryExpressionAdapter<String> {

    private QueryExpression queryExpression;

    public MysqlAdapter(QueryExpression queryExpression) {
        this.queryExpression = queryExpression;
    }

    @Override
    public String dialect() {
        if (queryExpression.getValue()==null) {
            return queryExpression.getField() + MysqlConstants.IS_NULL;
        }
        return doDialect(queryExpression.getField(),queryExpression.getValue());
    }

    protected abstract String doDialect(String field, Object value);
}
