package org.songdan.tij.designpattern.command;

/**
 * 命令调用者，
 * Created by SongDan on 2017/3/20.
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void invoke() {
        command.execute();
    }

}
