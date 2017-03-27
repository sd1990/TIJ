package org.songdan.tij.polymorphic.object_build;

/**
 * 在父类构造器中调用子类的方法
 */
public class PolyConstructors {

    public static void main(String[] args) {
        new RoundGlyph();
    }
}

class Glyph {

    private int radius = 1;

    void draw() {
        System.out.println("glyph draw..");
    }

    public Glyph() {
        super();
        System.out.println("Glyph constructor ..");
        System.out.println("Glyph radius is " + radius);
        //在父类构造器中调用子类的方法，会出现未知的行为，因为这个时候子类还没有完成初始化
        draw();
    }
}

class RoundGlyph extends Glyph {

    private int radius = 1;

    void draw() {
        System.out.println("roundGlyph draw " + radius);
    }

    public RoundGlyph() {
        System.out.println("RoundGlyph constructor ..");
        System.out.println("roundGlyph radius is " + radius);
    }
}