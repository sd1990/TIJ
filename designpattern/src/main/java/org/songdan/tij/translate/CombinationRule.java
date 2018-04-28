package org.songdan.tij.translate;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 13 四月 2018
 */
@JsonDeserialize(as = CombinationRule.class)
public class CombinationRule extends AbsRule {

    private List<AbsRule> rules;

    private String op;

	public List<AbsRule> getRules() {
		return rules;
	}

	public void setRules(List<AbsRule> rules) {
		this.rules = rules;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	@Override
    public Expression toExpression() {
        if (Relation.AND.name().toLowerCase().equals(op)) {
            return ExpressionBuilder.and(rules.stream().map(AbsRule::toExpression).collect(Collectors.toList())
                    .toArray(new Expression[rules.size()]));
        } else if (Relation.OR.name().toLowerCase().equals(op)) {
            return ExpressionBuilder.or(rules.stream().map(AbsRule::toExpression).collect(Collectors.toList())
                    .toArray(new Expression[rules.size()]));
        }
        return null;
    }
}
