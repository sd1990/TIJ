package org.songdan.tij.node;


public class Node {
    int value;
    Node next;
    public Node() {
        super();
        // TODO Auto-generated constructor stub
    }
    public Node(int value, Node next) {
        super();
        this.value = value;
        this.next = next;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(value+"--");
        Node next = this.next;
        while(next!=null){
            sb.append(next.value);
            sb.append("--");
            next=next.next;
        }
        return sb.toString();
    }
    
    
}

class NodeReverse{
    public Node reverse(Node head){
        if(head == null)
            return null;
        Node prev = head;
        Node curr = prev.next;
        Node next ;
        while(curr != null){
            next = curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        
        head.next=null;
        
        head =prev;
        
        return head;
    }
    
    
    public Node reverse2(Node head){
        if(head == null || null == head.next)
            return head;
        Node reverseNode = reverse2(head.next);
        head.next.next=head;
        head.next=null;
        return reverseNode;
    }
}
