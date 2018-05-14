package org.songdan.tij.query;

import lombok.Getter;

/**
 * 查询排序
 *
 * @author song dan
 * @since 10 五月 2018
 */
@Getter
public class QuerySort {

    private String field;

    private Order order;

    public QuerySort(String field, Order order) {
        this.field = field;
        this.order = order;
    }

    @Override
    public String toString() {
        return "{" + field + " " + order + "}";
    }

    enum Order {
        DESC, ASC;

    }
}
