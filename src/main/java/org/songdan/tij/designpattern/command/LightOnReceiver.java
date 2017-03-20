package org.songdan.tij.designpattern.command;

/**
 * Created by SongDan on 2017/3/20.
 */
public class LightOnReceiver implements Receiver {

    @Override
    public void action() {
        System.out.println("light on !");
    }
}
