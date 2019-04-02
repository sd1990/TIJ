package org.songdan.tij.algorithm;

/**
 * 链表算法,反转、
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

    /**
     * 去重
     * @param head
     * @return
     */
    public static Node distinct(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node cur = head;
        while (cur != null) {
            Object t = cur.t;
            Node prev = cur;
            Node target = cur.next;
            while (target != null) {
                Node next = target.next;
                if (t.equals(target.t)) {
                    //删除target
                    prev.next = next;
                } else {
                    prev = target;
                }
                target = next;
            }
            cur = cur.next;
        }
        return head;
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

        Node<Integer> head = new Node<>(null, 1).next(2).next(1).next(1).next(3).next(3);
        System.out.println(head);
        distinct(head);
        System.out.println(head);
    }


}
