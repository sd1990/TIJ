package org.songdan.tij.query.builder;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * 通用方法
 *
 * @author song dan
 * @since 10 五月 2018
 */
public abstract class CombinationBuilder implements QueryExpressionBuilder{

	private List<QueryExpressionBuilder> builders = Lists.newArrayList();

	/**
	 * 外层的组合Builder
	 */
	private CombinationBuilder combinationBuilder;

	public CombinationBuilder(CombinationBuilder combinationBuilder) {
		this.combinationBuilder = combinationBuilder;
	}

	protected boolean put(QueryExpressionBuilder builder) {
		return builders.add(builder);
	}

	/**
	 * eq
	 * @param field
	 * @param value
	 * @return
	 */
	public CombinationBuilder eq(String field,Object value){
		put(new EqBuilder(field, value));
		return this;
	}

	public OrBuilder orThen() {
		return new OrBuilder(this);
	}

	public CombinationBuilder end() {
		if (combinationBuilder != null) {
			combinationBuilder.put(this);
			return combinationBuilder;
		}
		return this;
	}

	public AndBuilder andThen() {
		return new AndBuilder(this);
	}

	protected List<QueryExpressionBuilder> getBuilders() {
		return builders;
	}
}
