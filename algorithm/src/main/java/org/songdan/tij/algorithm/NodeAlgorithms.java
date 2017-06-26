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


}
