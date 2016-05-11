package org.songdan.tij.array.designpattern.adaptepattern;


public class SubAdapteeClass extends AdapteeClass{

    @Override
    public void specificRequest() {
        System.out.println("subAdaptee specificRequest run ...");
    }
}
