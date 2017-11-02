package org.songdan.tij.designpattern.observers;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 02 十一月 2017
 */
public class HelloWorldEventListener implements EventListener<Task>{

	@Override
	public void onEvent(EventContext<Task> eventContext) {
		//do something
		System.out.println(eventContext.getEvent()+">>>"+eventContext.getTask().getName()+":hello world !!!");
	}

	@Override
	public boolean register(Event<Task> event) {
		return event.register(this);
	}

}
