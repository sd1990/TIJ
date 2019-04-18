package org.songdan.tij.query.adapter.mysql;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.songdan.tij.query.expression.In;

/**
 * @author: Songdan
 * @create: 2019-04-17 19:34
 **/
public class InAdapter extends MysqlAdapter{

    private In in;

    public InAdapter(In in) {
        super(in);
        this.in = in;
    }

    @Override
    public String doDialect(String field,Object value) {
        Object[] valueArr = (Object[])value;
        String content = Joiner.on(",").join(Lists.transform(Lists.newArrayList(valueArr), new Function<Object, String>() {
            @Override
            public String apply(Object input) {
                return MysqlToStringBuilder.toString(input);
            }
        }));
        return field + MysqlConstants.IN + "(" + content + ")";
    }
}
