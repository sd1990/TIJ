package org.songdan.tij.inherit.son;

public class SonSonClass extends SubClass {

    public SonSonClass() {
        super("abc");
    }

    @Override
    public String toString() {
        return "son son class ...";
    }

    public static void main(String[] args) {
        new SonSonClass();
    }
}
