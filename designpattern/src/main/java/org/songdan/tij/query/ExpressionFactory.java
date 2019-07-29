package org.songdan.tij.query;

import com.google.common.collect.Lists;

import lombok.Data;
import org.songdan.tij.query.builder.CombinationBuilder;
import org.songdan.tij.query.expression.QueryExpression;
import org.songdan.tij.query.expression.Relation;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 工厂类
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class ExpressionFactory {

    private ExpressionFactory() {

    }

    public static ExpressionFactory create() {
        return new ExpressionFactory();
    }

    public static void main(String[] args) {
        QueryExpression eqExpression = create().and().eq("name", "foo").build();
        QueryExpression andExpression = create().and().eq("name", "foo").eq("name", "foo").build();
        QueryExpression complexExpression = create().and().eq("name", "foo").eq("name", "foo").orThen()
                .eq("data.color", "red").eq("data.size", 12).end().build();
        QueryExpression complexExpression2 = create().or().eq("name", "foo").eq("name", "foo").andThen()
                .eq("data.color", "red").eq("data.size", 12).end().build();
        QueryExpression complexExpression3 = create().or().andThen().eq("data.color", "red").eq("data.size", 12).end()
                .andThen().eq("data.color", "red").eq("data.size", 12).end().build();

        QueryExpression complexExpression4 = create().and()
                .eq("name", "foo")
                .orThen()
                .andThen().eq("data.color", "red").eq("data.size", 12).end()
                .andThen().eq("data.color", "red").eq("data.size", 12).end()
//                .end()

                .build();

        System.out.println(complexExpression4);
        System.out.println(complexExpression4.adapter().dialect());
        QueryExpression variousExpression = create().and().eq("name", "foo").notEq("name", "foo").like("name", "foo")
                .missing("name").in("name", Lists.newArrayList("foo")).ge("age", 10).le("age", 5)
                .build();
        System.out.println(variousExpression);
        System.out.println(variousExpression.adapter().dialect());

    }

    public CombinationBuilder and() {
        return new CombinationBuilder(null, Relation.AND);
    }

    public CombinationBuilder or() {
        return new CombinationBuilder(null, Relation.OR);
    }

    @Data
    static class Demo {
        private String name;

        private Integer age;
    }

    @Data
    static class OrderCondition {
        /**
         * 订单开始时间
         */
        private long startTime;

        /**
         * 订单结束时间
         */
        private long endTime;

        /**
         * 用户id
         */
        private long userId;

        /**
         * 金额下限
         */
        private BigDecimal lowMoney;

        /**
         * 金额上限
         */
        private BigDecimal highMoney;

    }

}
