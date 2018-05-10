package org.songdan.tij.query;


import org.songdan.tij.query.builder.AndBuilder;
import org.songdan.tij.query.builder.EqBuilder;
import org.songdan.tij.query.builder.OrBuilder;

/**
 * 工厂类
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class ExpressionBuilderFactory {

    private ExpressionBuilderFactory() {

    }

    public static ExpressionBuilderFactory create() {
        return new ExpressionBuilderFactory();
    }

    public static void main(String[] args) {
        QueryExpression eqExpression = create().eq("name", "foo").build();
        System.out.println(eqExpression);
        QueryExpression andExpression = create().and().eq("name", "foo").eq("name", "foo").build();
        System.out.println(andExpression);
        QueryExpression complexExpression = create().and().eq("name", "foo").eq("name", "foo").orThen()
                .eq("data.color", "red")
				.eq("data.size",12)
				.end()
				.build();
		System.out.println(complexExpression);
        QueryExpression complexExpression2 = create().or().eq("name", "foo").eq("name", "foo").andThen()
                .eq("data.color", "red")
				.eq("data.size",12)
				.end()
				.build();
		System.out.println(complexExpression2);
        QueryExpression complexExpression3 = create().or()
				.andThen()
                .eq("data.color", "red")
				.eq("data.size",12)
				.end()
				.andThen()
                .eq("data.color", "red")
				.eq("data.size",12)
				.end()
				.build();
		System.out.println(complexExpression3);
		QueryExpression complexExpression4 = create().and().eq("name", "foo")
					.orThen()
						.andThen()
							.eq("data.color", "red")
							.eq("data.size",12)
						.end()
						.andThen()
							.eq("data.color", "red")
							.eq("data.size",12)
						.end()
					.end()
				.build();
		System.out.println(complexExpression4);
		QueryExpression complexExpression5 = create().and().eq("name", "foo")
					.end()
				.build();
		System.out.println(complexExpression5);
	}

    public EqBuilder eq(String field, Object value) {

        return new EqBuilder(field, value);
    }

    public AndBuilder and() {
        return new AndBuilder(null);
    }

    public OrBuilder or() {
        return new OrBuilder(null);
    }

}
