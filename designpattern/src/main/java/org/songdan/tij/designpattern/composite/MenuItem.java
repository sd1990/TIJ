package org.songdan.tij.designpattern.composite;

import java.util.Iterator;

/**
 * 菜单项，叶子节点
 * Created by SongDan on 2017/3/16.
 */
public class MenuItem implements MenuComponent {

    private String name;

    private String price;

    public MenuItem(String name, String price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    @Override
    public boolean add(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void print() {
        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < 2; j++) {
            builder.append("-");
        }
        System.out.println(builder.append(":").append(name).append("->").append(price).toString());
    }

    @Override
    public Iterator<MenuComponent> iterator() {
        return new NullIterator();
    }

    /*
    结合迭代器模式
     */
    class NullIterator implements Iterator<MenuComponent>{

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public MenuComponent next() {
            return null;
        }
    }
}
