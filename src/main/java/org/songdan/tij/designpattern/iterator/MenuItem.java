package org.songdan.tij.designpattern.iterator;

/**
 * 元素类型
 * @author Songdan
 * @date 2017/3/16 19:09
 */
public class MenuItem {

    private String name;

    private String description;

    private String price;

    private boolean vegetarian;

    public MenuItem(String description, String name, String price, boolean vegetarian) {
        this.description = description;
        this.name = name;
        this.price = price;
        this.vegetarian = vegetarian;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }
}
