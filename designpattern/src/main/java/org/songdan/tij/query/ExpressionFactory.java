package org.songdan.tij.query;

import com.google.common.collect.Lists;

import lombok.Data;
import org.songdan.tij.query.builder.CombinationBuilder;
import org.songdan.tij.query.expression.QueryExpression;
import org.songdan.tij.query.expression.Relation;

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
        System.out.println(eqExpression);
        QueryExpression andExpression = create().and().eq("name", "foo").eq("name", "foo").build();
        System.out.println(andExpression);
        QueryExpression complexExpression = create().and().eq("name", "foo").eq("name", "foo").orThen()
                .eq("data.color", "red").eq("data.size", 12).end().build();
        System.out.println(complexExpression);
        QueryExpression complexExpression2 = create().or().eq("name", "foo").eq("name", "foo").andThen()
                .eq("data.color", "red").eq("data.size", 12).end().build();
        System.out.println(complexExpression2);
        QueryExpression complexExpression3 = create().or().andThen().eq("data.color", "red").eq("data.size", 12).end()
                .andThen().eq("data.color", "red").eq("data.size", 12).end().build();
        System.out.println(complexExpression3);

        QueryExpression complexExpression4 = create().and()
                .eq("name", "foo")
                    .orThen()
                        .andThen().eq("data.color", "red").eq("data.size", 12).end()
                        .andThen().eq("data.color", "red").eq("data.size", 12).end()
                    .end()

                .build();
        System.out.println(complexExpression4);

        QueryExpression complexExpression5 = create().and().eq("name", "foo").end().build();
        System.out.println(complexExpression5);
        QueryExpression variousExpression = create().and().eq("name", "foo").notEq("name", "foo").like("name", "foo")
                .missing("name").in("name", Lists.newArrayList("foo")).ge("age", 10).le("age", 5)
                .build();
        System.out.println(variousExpression);

        Demo demo = new Demo();
        // demo.setAge(10);
        System.out.println(create().and().ge("name", demo.getName(), false).eq("age", demo.getAge())
                .in("name", null, false).build());
        // error demo
        System.out.println(create().and().eq("name", "foo").orThen().eq("name", "abc").build());
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

}
