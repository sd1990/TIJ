package org.songdan.tij.algorithm.string.trie;

import com.google.common.collect.Lists;

import java.util.*;

public class ACAutoMachine{
    private TrieNode root;

    public ACAutoMachine()  {
        root = new TrieNode();
    }

    /**
     * 传入List<String>构造trie
     * @param strings
     */
    public void build(List<String> strings) {
        root = new TrieNode();
        for (String str : strings) {
            add(str);
        }
    }
    /**
     * 向trie中添加新串
     * @param str
     */
    public void add(String str) {
        TrieNode curNode = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (curNode.getNext().get(c) == null) {
                TrieNode next = new TrieNode();
                curNode.getNext().put(c, next);
                next.setVal(curNode.getVal() + c);
            }
            curNode = curNode.getNext().get(c);
            if (i == str.length() - 1)
                curNode.setTail(true);
        }

    }

    /**
     * 判断 str是否含有trie中得单词
     * @param str
     * @return
     */
    public boolean canMatch(String str) {
        makeFail();
        TrieNode curNode = root;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            while (curNode.getNext().get(c) == null && curNode != root) {
                curNode = curNode.getFail();
            }
            if (curNode.getNext().get(c) != null) {
                curNode = curNode.getNext().get(c);
            }
            TrieNode tmp = curNode;
            while (tmp != root) {
                if (tmp.isTail()) {
                    return true;
                }
                tmp = tmp.getFail();
            }
        }
        if (curNode.isTail())
            return true;
        return false;
    }

    /**
     * 得到str中所有包含在trie中得字符串
     * @param str
     * @return
     */
    public List<String> match(String str) {
        makeFail();
        List<String> exist = new ArrayList<String>();
        TrieNode curNode = root;
        int len = str.length();
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            while (curNode.getNext().get(c) == null && curNode != root) {
                curNode = curNode.getFail();
            }
            if (curNode.getNext().get(c) != null) {
                curNode = curNode.getNext().get(c);
            }
            TrieNode tmp = curNode;
            while (tmp != root) {
                if (tmp.isTail()) {
//                    tmp.setTail(false);
                    exist.add(tmp.getVal());
                }
                tmp = tmp.getFail();
            }
        }
        if (curNode.isTail())
            exist.add(curNode.getVal());
        return exist;
    }

    /**
     * 构造fail指针
     */
    public void makeFail() {
        Queue<TrieNode> queue = new LinkedList<TrieNode>();
        Map<Character, TrieNode> map = root.getNext();
        for (Map.Entry<Character, TrieNode> entry : map.entrySet()) {
            TrieNode next = entry.getValue();
            next.setFail(root);
            queue.offer(next);
        }
        while (!queue.isEmpty()) {
            TrieNode curNode = queue.poll();
            for (Map.Entry<Character, TrieNode> entry : curNode.getNext().entrySet()) {
                Character nextC = entry.getKey();
                TrieNode tmp = curNode.getFail();
                while (tmp != root) {
                    if (tmp.getNext().get(nextC) == null)
                        tmp = tmp.getFail();
                    else
                        break;
                }
                if (tmp.getNext().get(nextC) != null) {

                    entry.getValue().setFail(tmp.getNext().get(nextC));
                } else {
                    entry.getValue().setFail(root);
                }

                //System.out.println(entry.getValue().getVal() + " ---------- " + entry.getValue().getFail().getVal() );
                queue.offer(entry.getValue());
            }
        }
    }

    public List<String> find(String str) {

        TrieNode node = this.root;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Map<Character, TrieNode> next = node.getNext();
            char c = chars[i];
            TrieNode subNode = next.get(c);
            if (subNode != null) {
                node = subNode;
            } else {
                return Lists.newArrayList();
            }
        }
        //
        return getConsequenceList(node);


    }

    private List<String> getConsequenceList(TrieNode node) {
        List<String> result = Lists.newArrayList();
        if (node.isTail()) {
            result.add(node.getVal());
        }
        Map<Character, TrieNode> next = node.getNext();
        if (next == null || next.isEmpty()) {
            return result;
        }
        for (Map.Entry<Character, TrieNode> nodeEntry : next.entrySet()) {
            TrieNode value = nodeEntry.getValue();
            result.addAll(getConsequenceList(value));
        }
        return result;
    }

    public void showTrie() {
        showTrie(root, 0);
    }
    private void showTrie(TrieNode curNode, int depth) {
        Map<Character, TrieNode> next = curNode.getNext();
        for (Map.Entry<Character, TrieNode> entry : next.entrySet()) {
            for (int i = 0; i < depth; i++) {
                System.out.print('\t');
            }
            System.out.println(entry.getKey());
            showTrie(entry.getValue(), depth + 1);
        }
    }

    public static void main(String[] args) {
        ACAutoMachine autoMachine = new ACAutoMachine();
        autoMachine.build(Lists.newArrayList("hello","hi","her"));
        autoMachine.showTrie();
        autoMachine.canMatch("he");
        System.out.println(autoMachine.match("hellol"));
        System.out.println(autoMachine.find("h"));
        System.out.println(autoMachine.find("he"));
    }
}
