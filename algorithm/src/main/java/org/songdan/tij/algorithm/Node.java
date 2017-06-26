package org.songdan.tij.algorithm;

/**
 * @author Songdan
 * @date 2017/6/26 15:35
 */
class Node<T>{

    T t;

    Node<T> next;

    public Node(Node<T> next, T t) {
        this.next = next;
        this.t = t;
    }
}
