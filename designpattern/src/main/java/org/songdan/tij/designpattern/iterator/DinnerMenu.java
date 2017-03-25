package org.songdan.tij.designpattern.iterator;

import java.util.Iterator;

/**
 * 内部由数组实现
 * 线程不安全
 * @author Songdan
 * @date 2017/3/16 19:08
 */
public class DinnerMenu implements Menu {

    private MenuItem[] menuItems = new MenuItem[16];

    private int cursor;


    @Override
    public void addItem(MenuItem menuItem) {
        if (cursor>=menuItems.length) {
            throw new IndexOutOfBoundsException("已经到达DinnerMenu的最大边界");
        }
        menuItems[cursor++] = menuItem;
    }

    @Override
    public Iterator<MenuItem> listIterator() {
        return new DinnerMenuIterator();
    }

    public int getSize() {
        return cursor;
    }

    public MenuItem remove(int index) {
        MenuItem menuItem = menuItems[index];
        menuItems[index] = null;
        cursor--;
        return menuItem;
    }



    class DinnerMenuIterator implements Iterator<MenuItem> {

        private int index;

        private int lastIndex = -1;

        @Override
        public boolean hasNext() {
            return index < cursor;
        }

        @Override
        public MenuItem next() {
            int i = index;
            index++;
            return menuItems[lastIndex=i];
        }

        @Override
        public void remove() {
            DinnerMenu.this.remove(lastIndex);
            index = lastIndex;
            lastIndex = -1;
        }
    }
}
