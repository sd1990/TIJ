package org.songdan.tij.query.adapter.mysql;

import org.songdan.tij.query.expression.Like;

/**
 * @author: Songdan
 * @create: 2019-04-17 20:28
 **/
public class LikeAdapter extends MysqlAdapter {


    public LikeAdapter(Like queryExpression) {
        super(queryExpression);
    }

    @Override
    protected String doDialect(String field, Object value) {
        if (!String.class.isInstance(value)) {
            throw new UnsupportedOperationException("like operation only support String Type!!!");
        }
        String content = MysqlToStringBuilder.toString(value);
        return field + MysqlConstants.LIKE + MysqlConstants.QUOTE+content.substring(1, content.length() - 1) +MysqlConstants.PERCENT+ MysqlConstants.QUOTE;
    }
}
