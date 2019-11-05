package org.songdan.tij.algorithm.node;

import java.util.Optional;

/**
 * 链表
 *
 * @author: Songdan
 * @create: 2019-10-29 10:25
 **/
public class LinkedList<T> {

    private Node head;

    private Node tail;

    public boolean addHead(T value) {
        Node old = head;
        Node node = new Node(value);
        head = node;
        if (old == null) {
            tail = node;
        } else {
            node.next = old;
        }
        return true;
    }

    public boolean addLast(T value) {
        Node node = new Node(value);
        Node old = tail;
        tail = node;
        if (old == null) {
            head = node;
        } else {
            old.next = node;
        }
        return true;
    }

    @Override
    public String toString() {
        return Optional.ofNullable(head).map(Object::toString).orElse("");
    }

    private class Node {

        public Node(T item) {
            this.item = item;
        }

        private T item;

        private Node next;

        @Override
        public String toString() {
            String str = "";
            if (item != null) {
                str += item;
            }
            if (next != null) {
                str += "\t";
                str += next.toString();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addLast("to");
        linkedList.addLast("be");
        linkedList.addLast("or");
        linkedList.addLast("not");
        linkedList.addLast("to");
        linkedList.addLast("be");
        System.out.println(linkedList);
    }

}
