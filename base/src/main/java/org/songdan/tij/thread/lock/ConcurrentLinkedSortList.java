package org.songdan.tij.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock实现交替锁,它只锁链表的一部分<br><br/>
 * 创建一个有序的安全的list
 *
 * @author Songdan
 * @date 2016/6/3
 */
public class ConcurrentLinkedSortList {

    private class Node {

        private int value;

        private Node pre;

        private Node next;

        private Lock lock = new ReentrantLock();

        public Node() {
        }

        public Node(int value, Node pre, Node next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }
    }

    private Node head;

    private Node tail;

    public ConcurrentLinkedSortList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public void insert(int value) {
        Node current = head;
        Node next = head.next;
        current.lock.lock();
        try {
            while (true) {
                next.lock.lock();
                try {
                    //如果到达尾节点或者插入的值大于它后面的值，在next节点前面插入一个节点
                    if (next == tail || value > next.value) {
                        Node node = new Node(value, current, next);
                        current.next = node;
                        next.pre = node;
                        return;
                    }
                }
                finally {
                    current.lock.unlock();
                }
                //修改迭代指向，继续遍历
                current = next;
                next = current.next;

            }
        }
        finally {
            next.lock.unlock();
        }
    }

    public int size() {
        int count = 0;
        Node current = tail;
        while (current.pre != head) {
            current.lock.lock();
            try {
                count++;
                current = current.pre;
            }
            finally {
                current.lock.unlock();
            }
        }
        return count;
    }

}
