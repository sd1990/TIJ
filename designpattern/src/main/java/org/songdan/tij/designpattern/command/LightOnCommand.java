package org.songdan.tij.designpattern.command;

/**
 * Created by SongDan on 2017/3/20.
 */
public class LightOnCommand implements Command {

    @Override
    public void execute() {
        new LightOnReceiver().action();
    }
}
