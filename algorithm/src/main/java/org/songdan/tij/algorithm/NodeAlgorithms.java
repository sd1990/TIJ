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
    public static Node reverse(Node head) {
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
    public static Node recursiveReverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node newHead = recursiveReverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 1-2-3-4-5-6-7-8
     * 1-2-5-4-3-8-7-6
     * @param node
     * @return
     */
    public static Node reverseKGroup(Node node,int k) {
        /**
         * 第一步反转
         * 1-2-3-4-5-6-7-8
         * 8-7-6-5-4-3-2-1
         */
        Node reverse = recursiveReverse(node);

        /*
         * 分组
         * 8-7-6，5-4-3，2-1
         */
        Node[] gNodes = new Node[(length(reverse) + k - 1) / k];
        for (int i = 0; i < gNodes.length; i++) {
            gNodes[i] = reverse;
            Node prev = null;
            for (int j = 0; j < k; j++) {
                prev = reverse;
                if (reverse.next != null) {
                    reverse = reverse.next;
                }
            }
            if (prev != null) {
                prev.next = null;
            }
        }
        /**
         * 反转最后一组
         */
        gNodes[gNodes.length - 1] = recursiveReverse(gNodes[gNodes.length - 1]);
        /**
         * 反向拼接
         */
        Node result = null;
        Node gTail = null;
        for (int i = gNodes.length-1; i >= 0; i--) {
            Node gNode = gNodes[i];
            if (result == null) {
                result = gNode;
            }
            if (gTail != null) {
                gTail.next(gNode);
            }
            Node prev = gNode;
            while (gNode!=null) {
                prev = gNode;
                gNode = gNode.next;
            }
            gTail = prev;
        }
        return result;

    }

    /**
     * 1-2-3-4-5-6-7-8
     * 3-2-1-6-5-4-7-8
     * @param node
     * @return
     */
    public static Node reverseKGroupRecursive(Node node,int k) {
        /**
         * 1. 递归的终止条件 node的length<k
         * 2. 递归公式 kNode+reverseKGroupRecursive(sequenceNode,k)
         */
        Node temp = node;
        for (int i = 1; i < k; i++) {
            temp = temp.next;
            if (temp == null) {
                return node;
            }
        }
        Node sequenceNode = temp.next;
        temp.next = null;
        Node kNode = recursiveReverse(node);
        node.next = reverseKGroupRecursive(sequenceNode, k);
        return kNode;
    }

    public static int length(Node node) {
        int count = 0;
        while (node!=null) {
            count++;
            node = node.next;
        }
        return count;
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

        Node<Integer> head = new Node<>(1);
        head.next(2).next(3).next(4).next(5);
        System.out.println(head);
        System.out.println(reverseKGroupRecursive(head,3));
//        System.out.println(reverseKGroup(head,3));
//        distinct(head);
//        System.out.println(head);
    }


}
