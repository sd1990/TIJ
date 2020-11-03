package org.songdan.tij.statictest;

public class StaticTest {

    public static void main(String[] args) {
        Person p1 = StaticDemo.p1;
        System.out.println(StaticDemo.Inner.class);
//        StaticTest.instance().tag("k1","v1").tag("k2","v2");
        StaticTest.simpleTag("k1", "v1").simpleTag("k2", "v2");
    }

    public static StaticTest instance() {
        // get from tl
        return null;
    }

    public StaticTest tag(String key, String value) {
        //TODO
        return null;
    }

    public static StaticTest simpleTag(String key, String value) {
        //TODO
        return null;
    }

}
