package org.songdan.tij.query.builder;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.songdan.tij.query.expression.CombinationExpression;
import org.songdan.tij.query.expression.Comparisons;
import org.songdan.tij.query.expression.QueryExpression;
import org.songdan.tij.query.expression.Relation;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;

/**
 * 组合Builder
 *
 * @author song dan
 * @since 10 五月 2018
 */
public class CombinationBuilder implements QueryExpressionBuilder {

    private List<QueryExpressionBuilder> builders = Lists.newArrayList();

    private Relation relation;

    /**
     * 外层的组合Builder
     */
    private CombinationBuilder outCombinationBuilder;

    public CombinationBuilder(CombinationBuilder combinationBuilder, Relation relation) {
        this.outCombinationBuilder = combinationBuilder;
        this.relation = relation;
    }

    protected boolean put(QueryExpressionBuilder builder) {
        return builders.add(builder);
    }

    public CombinationBuilder eq(String field, Object value) {
        return eq(field, value, true);
    }

    public CombinationBuilder eq(String field, Object value, boolean ignoreNull) {
        if (ignoreNull && Objects.isNull(value)) {
            return this;
        }
        put(new EqBuilder(field, value));
        return this;
    }

    public CombinationBuilder notEq(String field, Object value) {
        return notEq(field, value, true);
    }

    public CombinationBuilder notEq(String field, Object value, boolean ignoreNull) {
        if (ignoreNull && Objects.isNull(value)) {
            return this;
        }
        put(new NotEqBuilder(field, value));
        return this;
    }

    public CombinationBuilder like(String field, String value) {
        return like(field, value, true);
    }

    public CombinationBuilder like(String field, String value, boolean ignoreNull) {
        if (ignoreNull && Strings.isNullOrEmpty(value)) {
            return this;
        }
        put(new LikeBuilder(field, value));
        return this;
    }

    public CombinationBuilder in(String field, Collection<Object> value) {
        return in(field, value, true);
    }

    public CombinationBuilder in(String field, Collection<Object> value, boolean ignoreNull) {
        if (ignoreNull && (Objects.isNull(value) || value.isEmpty())) {
            return this;
        }
        put(new InBuilder(field, value));
        return this;
    }

    /**
     * 大于等于
     *
     * @return
     */
    public CombinationBuilder ge(String field, String value) {
        return ge(field, value, true);
    }

    public CombinationBuilder ge(String field, String value, boolean ignoreNull) {
        if (ignoreNull && Objects.isNull(value)) {
            return this;
        }
        put(new ComparesionBuilder(field, value, Comparisons.ge));
        return this;
    }

    /**
     * 小于等于
     *
     * @return
     */
    public CombinationBuilder le(String field, String value) {
        return le(field, value, true);
    }

    public CombinationBuilder le(String field, String value, boolean ignoreNull) {
        if (ignoreNull && Strings.isNullOrEmpty(value)) {
            return this;
        }
        put(new ComparesionBuilder(field, value, Comparisons.le));
        return this;
    }

    /**
     * 大于等于
     *
     * @return
     */
    public CombinationBuilder ge(String field, Number value) {
        return ge(field, value, true);
    }

    public CombinationBuilder ge(String field, Number value, boolean ignoreNull) {
        if (ignoreNull && Objects.isNull(value)) {
            return this;
        }
        put(new ComparesionBuilder(field, value, Comparisons.ge));
        return this;
    }

    /**
     * 小于等于
     *
     * @return
     */
    public CombinationBuilder le(String field, Number value) {
        return le(field, value, true);
    }

    public CombinationBuilder le(String field, Number value, boolean ignoreNull) {
        if (ignoreNull && Objects.isNull(value)) {
            return this;
        }
        put(new ComparesionBuilder(field, value, Comparisons.le));
        return this;
    }

    /**
     * xx is null
     *
     * @param field
     * @return
     */
    public CombinationBuilder missing(String field) {
        put(new MissingBuilder(field));
        return this;
    }

    public CombinationBuilder orThen() {
        return new CombinationBuilder(this, Relation.OR);
    }

    public CombinationBuilder end() {
        if (outCombinationBuilder != null) {
            CombinationBuilder temp = outCombinationBuilder;
            temp.put(this);
            outCombinationBuilder = null;
            return temp;
        }
        return this;
    }

    public CombinationBuilder andThen() {
        return new CombinationBuilder(this, Relation.AND);
    }

    protected List<QueryExpressionBuilder> getBuilders() {
        return builders;
    }

    @Override
    public QueryExpression build() {
        return new CombinationExpression(
                getBuilders().stream().map(QueryExpressionBuilder::build).collect(Collectors.toList()), relation);
    }
}
