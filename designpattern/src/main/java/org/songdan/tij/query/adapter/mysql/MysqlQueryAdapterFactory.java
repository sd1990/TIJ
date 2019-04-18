package org.songdan.tij.query.adapter.mysql;

import org.songdan.tij.query.adapter.QueryAdapterFactory;
import org.songdan.tij.query.adapter.QueryExpressionAdapter;
import org.songdan.tij.query.expression.*;

/**
 * @author: Songdan
 * @create: 2019-04-17 19:41
 **/
public class MysqlQueryAdapterFactory implements QueryAdapterFactory<String> {
    @Override
    public QueryExpressionAdapter<String> adapter(QueryExpression queryExpression) {
        if (queryExpression instanceof Equal) {
            return equal((Equal) queryExpression);
        }
        if (queryExpression instanceof In) {
            return in((In) queryExpression);
        }
        if (queryExpression instanceof Like) {
            return like((Like) queryExpression);
        }
        if (queryExpression instanceof NotEqual) {
            return notEqual((NotEqual) queryExpression);
        }
        if (queryExpression instanceof Missing) {
            return missing((Missing) queryExpression);
        }
        if (queryExpression instanceof ComparisonExpression) {
            return comparision((ComparisonExpression) queryExpression);
        }
        if (queryExpression instanceof CombinationExpression) {
            return combination((CombinationExpression) queryExpression);
        }
        throw new UnsupportedOperationException("mysql not support adapte the instance of " + queryExpression.getClass());
    }

    QueryExpressionAdapter<String> equal(Equal equal) {
        return new EqualAdapter(equal);
    }

    QueryExpressionAdapter<String> notEqual(NotEqual notEqual) {
        return new NotEqualAdapter(notEqual);
    }

    QueryExpressionAdapter<String> like(Like like) {
        return new LikeAdapter(like);
    }

    QueryExpressionAdapter<String> in(In in) {
        return new InAdapter(in);
    }

    QueryExpressionAdapter<String> missing(Missing missing) {
        return new MissingAdapter(missing);
    }

    QueryExpressionAdapter<String> comparision(ComparisonExpression comparisonExpression) {
        return new ComparisonAdapter(comparisonExpression);
    }

    public QueryExpressionAdapter<String> combination(CombinationExpression combinationExpression) {
        return new CombinationAdapter(combinationExpression,this);
    }
}
