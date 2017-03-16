package org.songdan.tij.designpattern.iterator;

import java.util.Iterator;

/**
 * 内部由数组实现
 * 
 * @author Songdan
 * @date 2017/3/16 19:08
 */
public class DinnerMenu implements Menu {

    private MenuItem[] menuItems = new MenuItem[16];

    @Override
    public void addItem(MenuItem menuItem) {

    }

    @Override
    public Iterator<MenuItem> listIterator() {
        return null;
    }
}
