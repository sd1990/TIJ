package org.songdan.tij.algorithm.path;

import java.util.Stack;

/**
 * @author: Songdan
 * @create: 2019-03-09 11:42
 **/
public class Node {

    private int value;

    private Node left;

    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public void preTraverse() {
        System.out.println(value);
        if (left!=null) {
            left.preTraverse();
        }
        if (right != null) {
            right.preTraverse();
        }
    }

    public void preOrder(Node biTree){
        Stack<Node> stack=new Stack<Node>();
        while(biTree!=null||!stack.isEmpty()){
            while(biTree!=null){
                System.out.print(biTree.value+",");
                stack.push(biTree);
                biTree=biTree.left;
            }
            if(!stack.isEmpty()){
                biTree=stack.pop();
                biTree=biTree.right;
            }
        }
    }

    public void midOrder(Node biTree){
        Stack<Node> stack= new Stack<>();
        while(biTree!=null||!stack.isEmpty()){
            while(biTree!=null){
                stack.push(biTree);
                biTree=biTree.left;
            }
            if(!stack.isEmpty()){
                biTree=stack.pop();
                System.out.println(biTree.value);
                biTree=biTree.right;
            }
        }
    }

    public void preOrder2(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node!=null || !stack.isEmpty()) {
            while (node!=null) {
                System.out.println(node.value);
                stack.push(node);
                node = node.left;
            }
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    public void midTraverse() {
        if (left!=null) {
            left.midTraverse();
        }
        System.out.println(value);
        if (right != null) {
            right.midTraverse();
        }
    }

    public void aftTraverse() {
        if (left!=null) {
            left.aftTraverse();
        }
        if (right != null) {
            right.aftTraverse();
        }
        System.out.println(value);
    }

    public static void main(String[] args) {

    }





}
