package org.songdan.tij.designpattern.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 客户类
 * Created by SongDan on 2017/3/16.
 */
public class Waiter {

    private List<Menu> menus;

    public Waiter(List<Menu> menus) {
        this.menus = menus;
    }

    public void printMenu() {
        for (Menu menu : menus) {
            Iterator<MenuItem> menuItemIterator = menu.listIterator();
            iteratorMenu(menuItemIterator);
        }
    }

    private void iteratorMenu(Iterator<MenuItem> menuItemIterator) {
        while (menuItemIterator.hasNext()) {
            MenuItem menuItem = menuItemIterator.next();
            System.out.println(menuItem.getName() + ";" + menuItem.getPrice());
        }
    }

    public static void main(String[] args) {
        List<Menu> menus = new ArrayList<>();
        DinnerMenu dinnerMenu = new DinnerMenu();
        dinnerMenu.addItem(new MenuItem("fresh egg", "egg", "1.0 元", false));
        dinnerMenu.addItem(new MenuItem("fresh qicai ", "qicai", "0.5 元", true));
        PancakeHouseMenu pancakeHouseMenu = new PancakeHouseMenu();
        pancakeHouseMenu.addItem(new MenuItem("hot bread", "bread", "10.0 元", false));
        menus.add(dinnerMenu);
        menus.add(pancakeHouseMenu);
        Waiter waiter = new Waiter(menus);
        waiter.printMenu();
    }
}
