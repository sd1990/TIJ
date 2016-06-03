package org.songdan.tij.node;

import java.util.Random;

/**
 * Node相关的算法
 * @author SONGDAN
 *
 */
public class NodeAlgorithm {
    /**
     * 循环反转
     * @param head
     * @return
     */
    public static Node reverseLoop(Node head){
        Node pre = head;
        Node cur = pre.next;
        while(cur!=null){
            Node next = cur.next;
            cur.next=pre;
            //--控制变量--
            pre=cur;
            cur = next;
        }
        head.next=null;
        head = pre;
        return head;
    }
    
    /**
     * 递归反转
     * @param head
     * @return
     */
    public static Node recursive(Node head){
        if(head.next==null){
            return head;
        }
        Node reverseNode = recursive(head.next);
        head.next.next=head;
        head.next=null;
        return reverseNode;
    }
    
    /**
     * 循环去重
     * @param head
     * @return
     */
    public static void dupli(Node head){
        /*
         * 使用依次和后面的每一个节点比较如果有相同的，移除后面的节点
         * 双重循环
         */
        Node curr=head;
        while(curr!=null){
            Node runner = curr;
            //当前的值和它以后的每一个值比较
            Node next = runner.next;
            while(next!=null){
                //把当前的节点和后面的节点比较
                if(curr.value==next.value){
                    //移除该节点
                    runner.next=runner.next.next;
                }else{
                    //移动runner
                    runner = runner.next;
                }
                next = runner.next;
            }
            
            //控制变量
            curr=curr.next;
        }
    }
    
    public static Node buildNode(int length,boolean isRandom){
        Node head;
        if(!isRandom){
            head = new Node(0,null);
            //用来保存新生成的node，作为上一个节点使用
            Node temp = new Node();
            for (int i = 0; i < length; i++) {
                Node node = new Node(i+1,null);
                if(i==0){
                    head.next=node;
                }else{
                    temp.next=node;
                }
                temp=node;
            }
        }else{
            Random r = new Random(47);
            head = new Node(r.nextInt(length),null);
            //用来保存新生成的node，作为上一个节点使用
            Node temp = new Node();
            for (int i = 0; i < length; i++) {
                Node node = new Node(r.nextInt(length),null);
                if(i==0){
                    head.next=node;
                }else{
                    temp.next=node;
                }
                temp=node;
            }
            
        }
        return head;
    }
    
    public static void main(String[] args) {
        Node head = buildNode(10, false);
        System.out.println(head);
        head = reverseLoop(head);
        System.out.println(head);
        head = recursive(head);
        System.out.println(head);
        head=buildNode(10, true);
        System.out.println(head);
        dupli(head);
        System.out.println(head);
    }
}
