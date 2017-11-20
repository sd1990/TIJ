package org.songdan.tij.designpattern.observers;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 02 十一月 2017
 */
public class DoActualEventListener extends EventListenerHelper<Task> {

	@Override
	public void onEvent(EventContext<Task> eventContext) {
		System.out.println(eventContext.getEvent()+">>>"+eventContext.getTask().getName()+":do something actual !!!");
	}

	@Override
	protected void initRegister() {
		register(TaskEvent.CREATE);
		register(TaskEvent.ACCEPT);
	}
}
