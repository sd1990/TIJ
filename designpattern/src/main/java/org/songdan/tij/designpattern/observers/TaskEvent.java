package org.songdan.tij.designpattern.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 02 十一月 2017
 */
public class TaskEvent extends AbstractEvent<Task> {

	public static final TaskEvent CREATE = new TaskEvent(1);
	public static final TaskEvent ACCEPT = new TaskEvent(2);

    private int code;

    private TaskEvent(int code) {
        this.code = code;
    }
}