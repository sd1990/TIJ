package org.songdan.tij.array.node;


public class DulpliNode {

    public static void dulpli(Node head){
        if(head == null||head.next==null){
            return;
        }
        Node curr = head;
        /*
         * 使用选择排序的思路去重，从第一个与它后面的每一个比较，把重复的去掉
         */
        while(curr!=null){
            /*
             * 比较的逻辑
             */
            int currValue=curr.value;
            Node runner = curr;
            Node nextNode = curr.next;
            while(nextNode!=null){
                //如果节点的值相同，去除该节点
                if(currValue==nextNode.value){
                    runner.next=runner.next.next;
                    nextNode = runner.next;
                }else{
                    runner=nextNode;
                    nextNode=runner.next;
                }
            }
            //改变curr的指向
            curr=curr.next;
        }
    }
    
    public static void main(String[] args) {
        Node head = new Node();
        head.value='a';
        Node next=null;
        for (int i = 0; i < 4; i++) {
            Node temp = new Node();
            temp.value=(char) (head.value+i);
            if(i==0){
                head.next=temp;
            }else{
                next.next=temp;
            }
            next=temp;
        }
        
        Node h = head;
        while(h!=null){
            System.out.println((char)h.value);
            h=h.next;
        }
        
        dulpli(head);
        while(head!=null){
            System.out.println((char)head.value);
            head=head.next;
        }
    }
}
