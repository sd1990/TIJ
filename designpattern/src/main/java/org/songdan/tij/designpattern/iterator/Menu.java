package org.songdan.tij.designpattern.iterator;

import java.util.Iterator;

/**
 * Head First设计模式迭代器模式
 * 菜单接口
 * @author Songdan
 * @date 2017/3/16 19:06
 */
public interface Menu {

    /**
     * 添加菜单项
     * @param menuItem
     */
    void addItem(MenuItem menuItem);

    Iterator<MenuItem> listIterator();
}
