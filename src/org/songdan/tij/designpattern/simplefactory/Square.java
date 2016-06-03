package org.songdan.tij.designpattern.simplefactory;

/**
 * 具体产品类
 * @author SONGDAN
 *
 */
public class Square extends DrawTools{

    @Override
    public void draw() {
        System.out.println("square draw ...");
    }

    @Override
    public void erase() {
        System.out.println("square erase ...");
    }

}
