package org.songdan.tij.algorithm;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * 深度优先算法
 *
 * @author song dan
 * @since 15 五月 2018
 */
public class DFS<T extends Morable<T>> {

    private T first;

    private Stack<T> stack = new Stack<>();

    public T search(T ele,T key, Comparator<T> comparator) {
        stack.push(ele);
        if (comparator.compare(ele, key) == 0) {
            return ele;
        }
        if (ele.more().isEmpty()) {
            return null;
        }
        for (T t : ele.more()) {
            T result = search(t, key, comparator);
            if (result != null) {
                return result;
            }
            else {
                stack.pop();
            }
        }
        return null;
    }

    public void searchStack(T ele,T key) {
        stack.push(ele);
        if (Objects.equals(ele, key)) {
            return;
        }
        if (ele.more().isEmpty()) {
            stack.pop();
            return;
        }
        for (T t : ele.more()) {
            searchStack(t, key);
            if (stack.contains(key)) {
                return;
            }
        }
    }

    public String getSearchPath(T ele,T key, Comparator<T> comparator) {
        search(ele, key, comparator);
        return Joiner.on("<-").join(stack);
    }

    public static void main(String[] args) {
        Morable.Person lucy = new Morable.Person("lucy", "23456789");
        Morable.Person lily = new Morable.Person("lily", "23456789");
        Morable.Person david = new Morable.Person("david", "23456789");
        Morable.Person love = new Morable.Person("love", "23456789");
        Morable.Person licha = new Morable.Person("licha", "23456789");

        lucy.add(lily);
        lucy.add(david);
        lucy.add(love);

        david.add(love);
        love.add(licha);

        DFS<Morable.Person> dfs = new DFS<>();
        String path = Joiner.on("->").join(dfs.stack.stream().map(Morable.Person::getName).collect(Collectors.toList()));
        System.out.println(path);
        DFS<Morable.Person> dfs2 = new DFS<>();
        dfs2.searchStack(lucy,licha);
        String path2 = Joiner.on("->").join(dfs2.stack.stream().map(Morable.Person::getName).collect(Collectors.toList()));
        System.out.println(path2);

    }


}