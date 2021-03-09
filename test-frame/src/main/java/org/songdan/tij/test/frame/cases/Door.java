package org.songdan.tij.test.frame.cases;

/**
 * @author: Songdan
 * @create: 2020-12-23 11:25
 **/
public class Door {

    private double width;

    private double hight;

    private int type;

    private int status;

    public Door(double width, double hight, int type) {
        this.width = width;
        this.hight = hight;
        this.type = type;
    }

    public void open() {
        status = 1;
    }

    public void close() {
        status = 0;
    }

    public boolean canIn(double width,double hight) {
        return width<= this.width && hight <= this.hight;
    }
}
