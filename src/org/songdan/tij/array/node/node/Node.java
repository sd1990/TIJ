package org.songdan.tij.array.node.node;

import java.util.LinkedList;
import java.util.List;

/**
 * 树形节点类
 * @author Songdan
 * @date 2016/5/25
 */
public class Node<T> {

    private T value;

    private List<Node<T>> sonList;

    public Node(T t) {
        this.value = t;
        this.sonList = new LinkedList<>();
    }

    public List<Node<T>> getSonList() {
        return sonList;
    }

    public void setSonList(List<Node<T>> sonList) {
        this.sonList = sonList;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Node<T> tNode : sonList) {
            sb.append(tNode.toString());
            sb.append("\r\n");
        }
        return "value : " + value.toString() + "\r\n\tsonList :\r\n\t" + "\t" + sb.toString();
    }
}
