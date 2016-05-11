package org.songdan.tij.array.designpattern.simplefactory;

/**
 * 具体产品类
 * @author SONGDAN
 *
 */
public class Circle extends DrawTools{

    @Override
    public void draw() {
        System.out.println("circle draw ...");
    }

    @Override
    public void erase() {
        System.out.println("circle erase ...");
    }

}
