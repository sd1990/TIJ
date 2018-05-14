package org.songdan.tij.query.adapter;

import org.songdan.tij.query.expression.CombinationExpression;
import org.songdan.tij.query.expression.ComparisonExpression;
import org.songdan.tij.query.expression.Equal;
import org.songdan.tij.query.expression.In;
import org.songdan.tij.query.expression.Like;
import org.songdan.tij.query.expression.Missing;
import org.songdan.tij.query.expression.NotEqual;

/**
 * 查询AdapterFactory
 *
 * @author song dan
 * @since 14 五月 2018
 */
public interface QueryAdapterFactory {

    static QueryAdapterFactory find() {
        throw new RuntimeException("通过反射获取");
    }

    QueryExpressionAdapter equal(Equal equal);

    QueryExpressionAdapter notEqual(NotEqual notEqual);

    QueryExpressionAdapter like(Like like);

    QueryExpressionAdapter in(In in);

    QueryExpressionAdapter missing(Missing missing);

    QueryExpressionAdapter comparision(ComparisonExpression comparisonExpression);

    QueryExpressionAdapter combination(CombinationExpression combinationExpression);

}
