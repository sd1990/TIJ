package org.songdan.tij.designpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 内部有ArrayList实现
 * @author Songdan
 * @date 2017/3/16 19:08
 */
public class PancakeHouseMenu implements Menu {

    private List<MenuItem> list = new ArrayList<>(16);

    @Override
    public void addItem(MenuItem menuItem) {
        list.add(menuItem);
    }

    @Override
    public Iterator<MenuItem> listIterator() {
        return list.iterator();
    }
}
