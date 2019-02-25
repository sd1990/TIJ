package org.songdan.tij.algorithm;

/**
 * 链表算法
 * @author Songdan
 * @date 2017/6/26 11:34
 */
public class NodeAlgorithms {

    /**
     * 链表for循环反转
     * @param head
     * @return
     */
    public Node reverse(Node head) {
        Node prev = head;
        Node current = prev.next;
        while (current!=null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
        return head;
    }

    /**
     * 递归反转
     * @param head
     * @return
     */
    public Node recursiveReverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node reHead = recursiveReverse(head);
        head.next.next = head;
        head.next = null;
        return reHead;
    }

    public static <T> void move(Node<T> source) {
        Node<T> head,tail = null;
        Node<T> next;
        Node<T> e = source;
        do {
            next = e.next;
            if (tail == null) {
                head = e;
            }else{
                tail.next = e;
            }
            tail = e;
        } while ((e = next) != null);
        tail.next = null;
        System.out.println(tail);
    }

    public static void main(String[] args) {
        Node<Integer> one = new Node<Integer>(null, 1);
        Node<Integer> two = new Node<Integer>(one, 1);
        Node<Integer> three = new Node<Integer>(two, 1);
        move(three);
    }


}
