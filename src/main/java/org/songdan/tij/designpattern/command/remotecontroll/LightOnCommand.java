package org.songdan.tij.designpattern.command.remotecontroll;

import org.songdan.tij.designpattern.command.Command;
import org.songdan.tij.designpattern.command.LightOnReceiver;

/**
 * Created by SongDan on 2017/3/20.
 */
public class LightOnCommand implements Command {

    @Override
    public void execute() {
        new LightOnReceiver().action();
    }
}
