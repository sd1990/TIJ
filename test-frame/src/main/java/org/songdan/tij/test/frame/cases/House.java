package org.songdan.tij.test.frame.cases;

/**
 * @author: Songdan
 * @create: 2020-12-23 11:25
 **/
public class House {

    private Door door;

    public House(Door door) {
        this.door = door;
    }

    public boolean comeIn(double width,double hight) {
        return door.canIn(width, hight);
    }
}
