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
                    //继续递归，防止下一个菜单是一个空菜单
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
                if (componentIterator instanceof Menu) {
                    stack.push(next.iterator());
                }
                return next;
            }else {
                return null;
            }
        }
    }
}
