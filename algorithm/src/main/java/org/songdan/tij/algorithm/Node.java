package org.songdan.tij.algorithm;

/**
 * @author Songdan
 * @date 2017/6/26 15:35
 */
class Node<T>{

    T t;

    Node<T> next;

    public Node() {
    }

    public Node(T t) {
        this.t = t;
    }

    public Node<T> next(T t) {
        if (next == null) {
            next = new Node<>(t);
        } else {
            next.next(t);
        }
        return next;
    }

    @Override
    public String toString() {
        if (next == null) {
            return String.valueOf(t);
        } else {
            return String.valueOf(t) + "-->" + next.toString();
        }
    }
}
