package org.songdan.tij.node;

/**
 * 节点反转
 *
 * @author Songdan
 * @date 2016/5/26
 */
public class NodeReverse2 {

    public static Node reverseLoop(Node head) {
        Node prev = head;
        Node curr = prev.next;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev.next = null;
            prev = curr;
            curr = next;
        }
        head.next = null;
        head = prev;
        return head;
    }

    public static Node reverseRecurise(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node reverseNode = reverseRecurise(head.next);
        head.next.next = head;
        head.next = null;
        return reverseNode;
    }

}
