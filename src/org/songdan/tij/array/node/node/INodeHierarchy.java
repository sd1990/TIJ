package org.songdan.tij.array.node.node;

/**
 * 该接口提供了判断了两个同一个类型的两个元素是否具备父子关系
 * @author Songdan
 * @date 2016/5/25
 */
public interface INodeHierarchy<T> {

    /**
     * 判断node1和node2是否可以为父子节点
     * @param pNode
     * @param sonNode
     * @return
     */
    boolean isHierarchy(T pNode, T sonNode);

    /**'
     * 判断node2是否是node1的后代节点
     * @param pNode
     * @param childrenNode
     * @return
     */
    boolean isHierarchyRecursion(T pNode, T childrenNode);
}
