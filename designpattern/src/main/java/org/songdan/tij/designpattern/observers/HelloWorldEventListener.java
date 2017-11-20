package org.songdan.tij.designpattern.observers;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 02 十一月 2017
 */
public class HelloWorldEventListener extends EventListenerHelper<Task>{

	@Override
	public void onEvent(EventContext<Task> eventContext) {
		//do something
		System.out.println(eventContext.getEvent()+">>>"+eventContext.getTask().getName()+":hello world !!!");
	}


	@Override
	protected void initRegister() {
		register(TaskEvent.CREATE);
	}
}
