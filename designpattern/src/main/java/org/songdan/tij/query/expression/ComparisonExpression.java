package org.songdan.tij.query.expression;

import com.google.common.base.Joiner;

import lombok.Getter;
import org.songdan.tij.query.adapter.QueryAdapterFactory;
import org.songdan.tij.query.adapter.QueryExpressionAdapter;

/**
 *
 * @author zhangshaoqiang
 * @date 2018/1/29
 */
@Getter
public class ComparisonExpression implements QueryExpression {

    private static final long serialVersionUID = -7035947359127717875L;

    private String field;

    private Object value;

    private Comparisons comparisons;

    public ComparisonExpression(String key, Object value, Comparisons comparisons) {
        this.field = key;
        this.value = value;
        this.comparisons = comparisons;
    }

    public Comparisons getComparisons() {
        return comparisons;
    }

    @Override
    public String toString() {
        if (value == null) {
            return Joiner.on(" ").join(field, comparisons.getSignature());
        }
        return Joiner.on(" ").join(field, comparisons.getSignature(), value);
    }

}
