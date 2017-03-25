package org.songdan.tij.designpattern.composite;

import java.util.Iterator;

/**
 * 组合模式中菜单的父接口
 * Created by SongDan on 2017/3/16.
 */
public interface MenuComponent {

    boolean add(MenuComponent component);

    void print();

    Iterator<MenuComponent> iterator();

}
