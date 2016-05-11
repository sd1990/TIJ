package org.songdan.tij.array.designpattern.adaptepattern;


public class ClientClass {
    public static void main(String[] args) {
        TargetClass target = new AdapterClassForClass();
        target.request();
        TargetClass target2 = new AdapterClassForObject(new AdapteeClass());
        target2.request();
        TargetClass target3 = new AdapterClassForObject(new SubAdapteeClass());
        target3.request();
    }
}
