package org.songdan.tij.query.expression;

import java.io.Serializable;

import org.songdan.tij.query.adapter.QueryAdapterFactory;
import org.songdan.tij.query.adapter.QueryAdapterFactoryBuilder;
import org.songdan.tij.query.adapter.QueryExpressionAdapter;


/**
 * 查询表达式
 *
 * @author song dan
 * @since 10 五月 2018
 */
public interface QueryExpression extends Serializable {

    long serialVersionUID = -6781577186340650624L;

    default QueryExpressionAdapter adapter(){
        return QueryAdapterFactoryBuilder.getFactory().adapter(this);
    }

    String getField();

    Object getValue();

}
