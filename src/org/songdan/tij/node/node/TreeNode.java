package org.songdan.tij.node.node;

import java.util.LinkedList;
import java.util.List;

/**
 * 树形节点类
 * @author Songdan
 * @date 2016/5/25
 */
public class TreeNode<T> {

    private T value;

    private List<TreeNode<T>> sonList;

    public TreeNode(T t) {
        this.value = t;
        this.sonList = new LinkedList<>();
    }

    public List<TreeNode<T>> getSonList() {
        return sonList;
    }

    public void setSonList(List<TreeNode<T>> sonList) {
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
        for (TreeNode<T> tNode : sonList) {
            sb.append(tNode.toString());
            sb.append("\r\n");
        }
        return "value : " + value.toString() + "\r\n\tsonList :\r\n\t" + "\t" + sb.toString();
    }
}
