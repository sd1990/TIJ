package org.songdan.tij.algorithm.node;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ListNode extends AbstractNode {

    private static final long serialVersionUID = -1457476908248073127L;
    private final List<ValueNode> list = new ArrayList<ValueNode>();

    @Override
    public Type getType() {
        return Type.list;
    }

    public void add(ValueNode node) {
        list.add(node);
    }

    @Override
    public Iterator<ValueNode> iterator() {
        return list.iterator();
    }

    @Override
    public ValueNode get(int childIndex) {
        return list.get(childIndex);
    }

    public List<ValueNode> getAll() {
        return Collections.unmodifiableList(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public String toJson() {
        return JacksonSupport.toJson(this);
    }

    /**
     * 
     * @param list
     * @return original list
     * 
     */
    public List<ValueNode> update(List<ValueNode> list) {
        List<ValueNode> originalList = Lists.newArrayList(this.list);
        this.list.clear();
        for (ValueNode valueNode : list) {
            this.list.add(valueNode);
        }
        return originalList;
    }

}
