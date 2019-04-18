package org.songdan.tij.query.adapter;

import org.songdan.tij.query.adapter.mysql.MysqlQueryAdapterFactory;
import org.songdan.tij.query.expression.*;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 查询AdapterFactory
 *
 * @author song dan
 * @since 14 五月 2018
 */
public interface QueryAdapterFactory<T> {

    QueryExpressionAdapter<T> adapter(QueryExpression queryExpression);

}
