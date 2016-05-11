package org.songdan.tij.array.node;

/**
 * 链表反转
 * @author SONGDAN
 *
 */
public class NodeReverses {

    
    public static Node reverse1(Node head){
        if(head==null){
            return head;
        }
        Node prev = head;
        Node curr = prev.next;
        while(curr!=null){
            Node next = curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        head.next=null;
        head = prev;
        return head;
    }
    
    
    public static Node reverse2(Node head){
        if(head == null || head.next == null){
            return head;
        }
        
        Node reverseNode = reverse2(head.next);
        head.next.next=head;
        head.next=null;
        
        return reverseNode;
    }
    
    
    public static void main(String[] args) {
        Node head = new Node();
        head.value='a';
        Node next=null;
        for (int i = 1; i < 4; i++) {
            Node temp = new Node();
            temp.value=(char) (head.value+i);
            if(i==1){
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
        
        head=reverse2(head);
        h = head;
        while(h!=null){
            System.out.println((char)h.value);
            h=h.next;
        }
    }
}
