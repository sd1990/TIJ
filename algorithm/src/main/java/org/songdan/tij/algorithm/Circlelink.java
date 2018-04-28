package org.songdan.tij.algorithm;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 环路
 *
 * @author song dan
 * @since 12 四月 2018
 */
public class Circlelink {

    public static void main(String[] args) {
        Node a = new Node(null, 1);
        Node b = new Node(a, 1);
        Node c = new Node(b, 1);
        System.out.println(c.hasCircle());
        a.next = b;
        System.out.println(c.hasCircle());
    }

    private static class Node {

        private Node next;

        private Integer value;

        public Node(Node next, Integer value) {
            this.next = next;
            this.value = value;
        }

        public boolean hasCircle() {
            Node n = this.next;
            Set<Node> nodes = new LinkedHashSet<>();
            while ((n != null)) {
                if (nodes.add(n)) {
                    n = n.next;
                } else {
                    return true;
                }
            }
            return false;
        }

    }

}
