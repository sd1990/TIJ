package org.songdan.tij.algorithm.string.trie;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class TrieNode {
    private Map<Character, TrieNode> next;
    private boolean tail;
    private TrieNode fail;
    private String val = "";

    public TrieNode() {
        next = new HashMap<Character, TrieNode>();
        tail = false;
        fail = null;
        val = "";
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Map<Character, TrieNode> getNext() {
        return next;
    }

    public void setNext(Map<Character, TrieNode> next) {
        this.next = next;
    }

    public boolean isTail() {
        return tail;
    }

    public void setTail(boolean tail) {
        this.tail = tail;
    }

    public TrieNode getFail() {
        return fail;
    }

    public void setFail(TrieNode fail) {
        this.fail = fail;
    }
}
