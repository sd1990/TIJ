package org.songdan.tij.algorithm.node;

import java.io.Serializable;
import java.util.List;

public class ValueNodeWrapper implements Serializable {

    private Type type;
    private ValueNode single;
    private List<ValueNode> list;

    public static ValueNodeWrapper buildSingle(ValueNode valueNode) {
        ValueNodeWrapper wrapper = new ValueNodeWrapper();
        wrapper.setType(Type.Single);
        wrapper.setSingle(valueNode);
        return wrapper;
    }

    public static ValueNodeWrapper buildList(List<ValueNode> valueNodeList) {
        ValueNodeWrapper wrapper = new ValueNodeWrapper();
        wrapper.setType(Type.List);
        wrapper.setList(valueNodeList);
        return wrapper;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public ValueNode getSingle() {
        return single;
    }

    public void setSingle(ValueNode single) {
        this.single = single;
    }

    public List<ValueNode> getList() {
        return list;
    }

    public void setList(List<ValueNode> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ValueNodeWrapper");
        sb.append("{type=").append(type);
        sb.append(", single=").append(single);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }

    enum Type {
        List, Single;
    }
}
