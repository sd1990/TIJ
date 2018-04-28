package org.songdan.tij.translate;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合条件
 *
 * @author song dan
 * @since 13 四月 2018
 */
public class Combination implements Expression {

    private List<Expression> expressions = new LinkedList<>();

    private Relation relation;

    public Combination(Relation relation) {
        this.relation = relation;
    }

    public Combination add(Expression expression) {
        expressions.add(expression);
        return this;
    }

    @Override
    public String translate() {
        if (expressions.size() == 0) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        Iterator<Expression> iterator = expressions.iterator();
        builder.append(" ( ");
        if (iterator.hasNext()) {
            builder.append(iterator.next().translate());
        }
        while (iterator.hasNext()) {
            builder.append(" ").append(relation.name()).append(" ").append(iterator.next().translate());
        }
        builder.append(" ) ");
        return builder.toString();
    }
}
