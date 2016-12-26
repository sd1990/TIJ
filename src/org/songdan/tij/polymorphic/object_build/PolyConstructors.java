package org.songdan.tij.polymorphic.object_build;

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