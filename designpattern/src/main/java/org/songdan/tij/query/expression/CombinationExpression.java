package org.songdan.tij.query.expression;

import java.util.List;

import com.google.common.base.Joiner;

import lombok.Getter;
import org.songdan.tij.query.adapter.QueryAdapterFactory;
import org.songdan.tij.query.adapter.QueryExpressionAdapter;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 10 五月 2018
 */
@Getter
public class CombinationExpression implements QueryExpression {

    private List<QueryExpression> expressionList;

    private Relation relation;

    public CombinationExpression(List<QueryExpression> expressionList, Relation relation) {
        this.expressionList = expressionList;
        this.relation = relation;
    }

    @Override
    public String toString() {
        return "(" + Joiner.on(" " + relation.getSymbol() + " ").join(expressionList) + ")";
    }

    @Override
    public QueryExpressionAdapter adapter() {
        return QueryAdapterFactory.find().combination(this);
    }

}
