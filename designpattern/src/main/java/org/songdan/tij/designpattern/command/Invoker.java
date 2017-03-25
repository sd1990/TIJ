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

    public static void main(String[] args) {
        /*
        命令模式将命令的调用者和命令的接收者解耦，命令的调用者不需要知道命令的实际执行者
         */
        Command command = new LightOnCommand();
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.invoke();
    }
}
