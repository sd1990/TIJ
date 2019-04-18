package org.songdan.tij.query.adapter.mysql;

import org.songdan.tij.query.expression.Equal;

/**
 * @author: Songdan
 * @create: 2019-04-17 19:42
 **/
public class EqualAdapter extends MysqlAdapter {

    private Equal equal;

    public EqualAdapter(Equal equal) {
        super(equal);
        this.equal = equal;
    }

    @Override
    public String doDialect(String field, Object value) {
        return field+MysqlConstants.EQUAL +MysqlToStringBuilder.toString(value);
    }
}
