package org.songdan.tij.query;

import java.util.Objects;

import org.songdan.tij.query.expression.QueryExpression;

import lombok.Data;

/**
 * 工单查询Builder
 *
 * @author song dan
 * @since 10 五月 2018
 */
@Data
public class OrderQuery {

    private QueryExpression expressionBuilder;

    private QuerySort sort;

    private OrderQuery() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static void main(String[] args) {
        OrderQuery orderQuery = OrderQuery.builder().sort(new QuerySort("age", QuerySort.Order.ASC))
                .queryExpression(ExpressionFactory.create().and().eq("name", "foo").build()).build();
        System.out.println(orderQuery);
    }

    @Override
    public String toString() {
        return "OrderQuery{" + "expressionBuilder=" + expressionBuilder + ", sort=" + sort + '}';
    }

    public static class Builder {

        private QueryExpression queryExpression;

        private QuerySort sort;

        public Builder sort(QuerySort querySort) {
            this.sort = querySort;
            return this;
        }

        public Builder queryExpression(QueryExpression queryExpression) {
            this.queryExpression = queryExpression;
            return this;
        }

        public OrderQuery build() {
            if (Objects.isNull(queryExpression)) {
                throw new IllegalArgumentException("查询条件不能为空");
            }
            if (Objects.isNull(sort)) {
                sort = new QuerySort("main.id", QuerySort.Order.ASC);
            }
            OrderQuery orderQuery = new OrderQuery();
            orderQuery.expressionBuilder = queryExpression;
            orderQuery.sort = sort;
            return orderQuery;
        }

    }
}
