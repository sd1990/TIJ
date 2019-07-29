package org.songdan.tij.designpattern.composite;

import java.util.*;

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
        return new LeafIterator(list.listIterator());
    }


    /*
    组合模式结合迭代器模式
     */

    class CompositeIterator implements Iterator<MenuComponent>{

        private Stack<ListIterator<MenuComponent>> stack = new Stack<>();

        public CompositeIterator(ListIterator<MenuComponent> iterator) {
            stack.push(iterator);
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }else{
                ListIterator<MenuComponent> peek = stack.peek();
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
                    ListIterator<MenuComponent> iterator = ((Menu)next).list.listIterator();
                    stack.push(iterator);
                }
                return next;
            } else {
                return null;
            }
        }
    }

    class LeafIterator implements Iterator<MenuComponent>{

        private Stack<ListIterator<MenuComponent>> stack = new Stack<>();

        public LeafIterator(ListIterator<MenuComponent> iterator) {
            stack.push(iterator);
        }

        @Override
        public boolean hasNext() {
            if (stack.isEmpty()) {
                return false;
            }else{
                ListIterator<MenuComponent> peek = stack.peek();
                if (!peek.hasNext()) {
                    stack.pop();
                    //继续递归
                    return hasNext();
                }
                MenuComponent next = peek.next();
                if (!Menu.class.isInstance(next)) {
                    //回退
                    peek.previous();
                    return true;
                } else {
                    Menu nextMenu = (Menu) next;
                    stack.push(nextMenu.list.listIterator());
                    return hasNext();
                }
            }
        }

        @Override
        public MenuComponent next() {
            Iterator<MenuComponent> componentIterator = stack.peek();
            MenuComponent next = componentIterator.next();
            return next;
        }
    }
}
