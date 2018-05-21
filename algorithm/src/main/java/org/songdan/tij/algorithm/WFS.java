package org.songdan.tij.algorithm;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.google.common.collect.Lists;

/**
 * 广度优先算法
 *
 * @author song dan
 * @since 15 五月 2018
 */
public class WFS<T extends Morable<T>> {

    private Queue<T> queue;

    public WFS(Queue<T> queue) {
        this.queue = queue;
    }

    public WFS(T t) {
        this.queue = new LinkedList<>();
        queue.offer(t);
    }

    public static void main(String[] args) {
        Morable.Person 小王 = new Morable.Person("小王", "123456789");
        Morable.Person 小李 = new Morable.Person("小李", "5699543567");
        小王.add(小李);
        Morable.Person 老刘 = new Morable.Person("老刘", "5693435635");
        小王.add(老刘);
        老刘.add(new Morable.Person("老李", "098985644"));
        Morable.Person 老刘1 = new Morable.Person("老刘", "5693435635");
        小李.add(老刘1);
        Morable.Person 老王 = new Morable.Person("老王", "098985644");
        老刘.add(new Morable.Person("老王", "098985644"));
        老刘1.add(new Morable.Person("老王", "098985644"));
        System.out.println(小王.search(老王).path());
        System.out.println(小王.searchWithStep(老王).path());
    }

    public T search(T key, Comparator<T> comparator) {
        while (!queue.isEmpty()) {
            T ele = queue.poll();
            if (comparator.compare(key, ele) == 0) {
                return ele;
            }
            Collection<T> more = ele.more();
            more.forEach(t -> {
                queue.offer(t);
            });
        }
        return null;
    }

    public T searchWithLevel(T key, Comparator<T> comparator) {
        int step = 0;
        T targe = null;
        outer: while (!queue.isEmpty()) {
            step++;
            List<T> nextLevelEle = Lists.newArrayList();
            while (!queue.isEmpty()) {
                T ele = queue.poll();
                if (comparator.compare(key, ele) == 0) {
                    targe = ele;
                    break outer;
                }
                nextLevelEle.addAll(ele.more());
            }
            nextLevelEle.forEach(t -> {
                queue.offer(t);
            });
        }
        System.out.println("step is " + step);
        return targe;
    }

}