package org.songdan.tij.algorithm;

/**
 * 单向链表
 * @author Songdan
 * @date 2016/12/27 10:55
 */
public class NodeList<T> {

    private Node<T> head = new Node<>();

    public int size() {
        int i = 0;
        Node<T> node = head;
        while (node.next != null) {
            i++;
            node = node.next;
        }
        //首节点不包含在其中
        return i;
    }

    /**
     * 在下标为i元素插入元素e
     * @param e 数据
     * @param i 下标
     */
    public void insert(T e, int i) {
        int j = 0;
        Node<T> node = head;
        while (node.next != null && j < i) {
            j++;
            node = node.next;
        }
        node.next(e);
    }

    /**
     * 移除下标为i的元素
     * @param i
     * @return 移除元素
     */
    public T remove(int i) {
        int j = 0;
        Node<T> node = head;
        while (node.next != null && j < i) {
            j++;
            node = node.next;
        }
        Node<T> removeNode = node.next;
        node.next = removeNode.next;
        return removeNode.t;
    }

    public T get(int i) {
        int j = 0;
        Node<T> node = head.next;
        while (node.next != null && j < i) {
            j++;
            node = node.next;
        }
        return node.t;
    }

}

