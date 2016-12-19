package org.songdan.tij.innerclass;

/**
 * 模拟迭代器模式;2015/11/27做出修改，为了体现内部类的灵活性，再扩展一个内部类
 *
 * @author SONGDAN
 */
public class Sequence {

    private Object[] items;

    private int next = 0;

    public Sequence(int size) {
        super();
        this.items = new Object[size];
    }

    ;

    public void add(Object o) {
        if (next < items.length) {
            items[next++] = o;
        }
    }

    private class SequenceSelector implements Selector {

        private int i = 0;

        @Override
        public boolean end() {
            return i != items.length;
        }

        @Override
        public Object next() {
            return items[i++];
        }

    }

    private class ReverseSelector implements Selector {

        private int i = items.length - 1;

        @Override
        public boolean end() {
            return i > -1;
        }

        @Override
        public Object next() {
            return items[i--];
        }

    }

    public Selector selector() {
        return new SequenceSelector();
    }

    public Selector reverSelector() {
        return new ReverseSelector();
    }

    public static void main(String[] args) {
        Sequence s = new Sequence(10);
        for (int i = 0; i < 10; i++) {
            s.add(i);
        }
        Selector selector = s.selector();
        while (selector.end()) {
            System.out.println(selector.next());
        }
        Selector reverSelector = s.reverSelector();
        while (reverSelector.end()) {
            System.out.println(reverSelector.next());
        }
    }
}
