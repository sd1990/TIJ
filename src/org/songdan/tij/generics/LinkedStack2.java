package org.songdan.tij.generics;

public class LinkedStack2<T> {

    /**
     * 静态内部类
     *
     * @param <U>
     * @author SONGDAN
     */
    private class Node {

        T value;

        Node next;

        Node() {
            value = null;
            next = null;
        }

        Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        boolean end() {
            return value == null && next == null;
        }
    }

    /**
     * 首节点
     */
    private Node top = new Node();

    public void push(T item) {
        top = new Node(item, top);
    }

    public T pop() {
        T t = top.value;
        if (!top.end()) {
            top = top.next;
        }
        return t;
    }

    public static void main(String[] args) {
        LinkedStack2<String> lss = new LinkedStack2<>();
        for (String string : "hello world! java".split(" ")) {
            lss.push(string);
        }
        String s;
        while ((s = lss.pop()) != null) {
            System.out.println(s);
        }
    }

}
