package org.songdan.tij.query.adapter.mysql;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import org.songdan.tij.query.adapter.QueryExpressionAdapter;
import org.songdan.tij.query.expression.CombinationExpression;
import org.songdan.tij.query.expression.QueryExpression;
import org.songdan.tij.query.expression.Relation;

import java.util.List;

import static org.songdan.tij.query.adapter.mysql.CombinationAdapter.MysqlRelation.by;
import static org.songdan.tij.query.adapter.mysql.MysqlConstants.AND_JOINER;

/**
 * @author: Songdan
 * @create: 2019-04-17 20:56
 **/
public class CombinationAdapter implements QueryExpressionAdapter<String> {

    private CombinationExpression combinationExpression;

    private MysqlQueryAdapterFactory mysqlQueryAdapterFactory;

    public CombinationAdapter(CombinationExpression queryExpression,MysqlQueryAdapterFactory mysqlQueryAdapterFactory) {
        this.combinationExpression = queryExpression;
        this.mysqlQueryAdapterFactory = mysqlQueryAdapterFactory;
    }

    @Override
    public String dialect() {
        List<QueryExpression> expressionList = combinationExpression.getExpressionList();
        MysqlRelation mysqlRelation = by(combinationExpression.getRelation());
        Preconditions.checkNotNull(mysqlRelation, "mysql relation can't be null");
        String sql = mysqlRelation.symbol.join(Lists.transform(expressionList, new Function<QueryExpression, String>() {
            @Override
            public String apply(QueryExpression input) {
                return mysqlQueryAdapterFactory.adapter(input).dialect();
            }
        }));
        if (expressionList.size()>1) {
            return "(" + sql + ")";
        }
        return sql;
    }

    enum MysqlRelation{
        AND(Relation.AND,MysqlConstants.AND_JOINER),
        OR(Relation.OR,MysqlConstants.OR_JOINER);

        Relation relation;

        Joiner symbol;

        MysqlRelation(Relation relation, Joiner symbol) {
            this.relation = relation;
            this.symbol = symbol;
        }

        static MysqlRelation by(Relation relation) {
            for (MysqlRelation mysqlRelation : values()) {
                if (mysqlRelation.relation.equals(relation)) {
                    return mysqlRelation;
                }
            }
            return null;
        }
    }
}
