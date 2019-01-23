package org.songdan.tij.designpattern.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 组合模式的父节点
 * Created by SongDan on 2017/3/16.
 */
public class Menu implements MenuComponent{

    private String name;

    private String description;

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Menu(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private List<MenuComponent> list = new ArrayList<>();

    @Override
    public boolean add(MenuComponent component) {
        return list.add(component);
    }

    /**
     * 这一种是内部遍历，没有借助迭代器
     */
    @Override
    public void print() {
        System.out.println(name +"-->"+description);
        System.out.println("========================");
        Iterator<MenuComponent> iterator = list.iterator();
        while (iterator.hasNext()) {
            MenuComponent component = iterator.next();
            component.print();
        }
    }

    @Override
    public Iterator<MenuComponent> iterator() {
        return new CompositeIterator(list.iterator());
    }


    /*
    组合模式结合迭代器模式
     */

    class CompositeIterator implements Iterator<MenuComponent>{

        private Stack<Iterator<MenuComponent>> stack = new Stack<>();

        public CompositeIterator(Iterator<MenuComponent> iterator) {
            stack.push(iterator);
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }else{
                Iterator<MenuComponent> peek = stack.peek();
                if (!peek.hasNext()) {
                    stack.pop();
                    //继续递归
                    return hasNext();
                }
                return true;
            }
        }

        @Override
        public MenuComponent next() {
            if (hasNext()) {
                Iterator<MenuComponent> componentIterator = stack.peek();
                MenuComponent next = componentIterator.next();
                if (next instanceof Menu) {
                    //这个地方使用list.iterator是为了防止下次调用componentIterator.next()产生递归调用，导致同一个Menu出现在多个stack中，重复
                    Iterator<MenuComponent> iterator = ((Menu)next).list.iterator();
                    stack.push(iterator);
                }
                return next;
            } else {
                return null;
            }
        }
    }
}
