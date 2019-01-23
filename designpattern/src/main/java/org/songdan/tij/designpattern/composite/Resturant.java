package org.songdan.tij.designpattern.composite;

import java.util.Iterator;

/**
 * @author: Songdan
 * @create: 2019-01-21 21:19
 **/
public class Resturant {

    public static void main(String[] args) {
        Menu menu = new Menu("河南菜", "xxx");
        Menu inner0 = new Menu("郑州菜", "ZZZ");
//        inner0.add(new MenuItem("胡辣汤", "10"));
//        inner0.add(new MenuItem("大盘鸡", "70"));
        menu.add(inner0);
        menu.add(new MenuItem("红烧肉", "20"));
        menu.add(new MenuItem("蚂蚁上树", "15"));
        Menu inner1 = new Menu("信阳菜", "YYY");
        inner1.add(new MenuItem("罗山黑猪", "100"));
        menu.add(inner1);
        Menu inner2 = new Menu("南阳菜", "YYY");
        inner2.add(new MenuItem("柴锅老黄牛", "1000"));
        Menu inner3 = new Menu("内乡菜", "CCC");
        inner3.add(new MenuItem("烩羊杂汤", "99"));
        inner3.add(new MenuItem("烩面", "15"));
        inner2.add(inner3);
        menu.add(inner2);
        Iterator<MenuComponent> iterator = menu.iterator();
        while (iterator.hasNext()) {
            MenuComponent next = iterator.next();
            System.out.println(next);
        }
    }

}
