package org.songdan.tij.algorithm.string.trie;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: Songdan
 * @create: 2019-08-15 13:55
 **/
public class TrieTree {

    private TrieNode root = TrieNode.ROOT;

    public boolean offer(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        char[] chars = str.toCharArray();
        TrieNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            TrieNode trieNode = temp.trieNodeMap.get(c);
            if (trieNode == null) {
                trieNode = new TrieNode(temp, c, false);
                temp.trieNodeMap.put(c, trieNode);
            }
            temp = trieNode;
        }
        temp.tail = true;
        return true;
    }

    public List<String> prefixOf(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return Lists.newArrayList();
        }
        char[] chars = prefix.toCharArray();
        TrieNode temp = root;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            TrieNode nextNode = temp.trieNodeMap.get(c);
            if (nextNode == null) {
                return Lists.newArrayList();
            }
            temp = nextNode;
        }
        //深度遍历所有的叶子节点
        List<TrieNode> leafs = temp.tails();
        return leafs.stream().map(TrieNode::getValue).collect(Collectors.toList());
    }

    public void showTree() {
        print0(root, "");
    }

    private void print0(TrieNode node, String tab) {
        System.out.println(tab + (node.tail ? "[" + node.c + "]" : node.c));
        for (TrieNode trieNode : node.trieNodeMap.values()) {
            print0(trieNode, tab + "\t");
        }
    }

    public static void main(String[] args) {
        TrieTree trieTree = new TrieTree();
        trieTree.offer("h");
        trieTree.offer("hhh");
        trieTree.offer("hi");
        trieTree.offer("hello");
        Random random = new Random();
        char[] chs = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n'};
        for (int i = 0; i < 10; i++) {
            int length = random.nextInt(10);
            StringBuilder builder = new StringBuilder();
            for (int j = 0; j < length; j++) {
                builder.append(chs[random.nextInt(chs.length)]);
            }
            trieTree.offer(builder.toString());
        }
        trieTree.showTree();
        System.out.println(trieTree.prefixOf("a"));
        System.out.println(trieTree.prefixOf("h"));
    }

    static class TrieNode {

        static final TrieNode ROOT = new TrieNode("/".toCharArray()[0], false);
        private char c;

        private Map<Character, TrieNode> trieNodeMap = Maps.newHashMap();

        private boolean tail;

        private TrieNode prev;

        TrieNode(char c, boolean tail) {
            this.c = c;
            this.tail = tail;
        }

        TrieNode(TrieNode prev, char c, boolean tail) {
            this.prev = prev;
            this.c = c;
            this.tail = tail;
        }

        void prev(TrieNode prev) {
            this.prev = prev;
        }

        public String getValue() {
            if (this == ROOT) {
                return "";
            }
            return prev.getValue() + c;
        }

        public Calculate<String> lazyValue() {
            if (this == ROOT) {
                return () -> "";
            }
            return () -> prev.lazyValue().execute() + c;
        }

        public List<TrieNode> tails() {
            return leaf0(this);
        }

        private List<TrieNode> leaf0(TrieNode trieNode) {
            List<TrieNode> list = Lists.newArrayList();
            if (trieNode.tail) {
                list.add(trieNode);
            }
            for (Map.Entry<Character, TrieNode> nodeEntry : trieNode.trieNodeMap.entrySet()) {
                TrieNode node = nodeEntry.getValue();
                list.addAll(leaf0(node));
            }
            return list;
        }
    }

    interface Calculate<T>{
        T execute();
    }

}
