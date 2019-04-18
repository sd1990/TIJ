package org.songdan.tij.query.adapter.mysql;

import com.google.common.base.Preconditions;
import org.songdan.tij.query.expression.ComparisonExpression;
import org.songdan.tij.query.expression.Comparisons;

import static org.songdan.tij.query.adapter.mysql.ComparisonAdapter.MysqlComparison.by;

/**
 * @author: Songdan
 * @create: 2019-04-17 20:44
 **/
public class ComparisonAdapter extends MysqlAdapter {

    private ComparisonExpression comparisonExpression;

    public ComparisonAdapter(ComparisonExpression queryExpression) {
        super(queryExpression);
        this.comparisonExpression = queryExpression;
    }

    @Override
    protected String doDialect(String field, Object value) {
        Comparisons comparisons = comparisonExpression.getComparisons();
        Preconditions.checkNotNull(comparisons, "comparisons can't be null");
        MysqlComparison mysqlComparison = by(comparisons);
        Preconditions.checkNotNull(mysqlComparison, "mysql comparisons can't be null");
        return field+ mysqlComparison.symbol+MysqlToStringBuilder.toString(value);
    }

    enum MysqlComparison{
        lt(Comparisons.lt,MysqlConstants.LT), gt(Comparisons.gt,MysqlConstants.GT), le(Comparisons.le,MysqlConstants.LE), ge(Comparisons.ge,MysqlConstants.GE);

        private Comparisons comparisons;

        private String symbol;

        MysqlComparison(Comparisons comparisons, String symbol) {
            this.comparisons = comparisons;
            this.symbol = symbol;
        }

        static MysqlComparison by(Comparisons comparisons) {

            for (MysqlComparison mysqlComparison : values()) {
                if (mysqlComparison.comparisons.equals(comparisons)) {
                    return mysqlComparison;
                }
            }
            return null;
        }
    }
}
