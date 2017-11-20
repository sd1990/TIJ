package org.songdan.tij.designpattern.observers;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 02 十一月 2017
 */
public class EventSource {

	public static EventContext<Task> generateEvent(TaskEvent event) {
		EventContext<Task> eventContext = new EventContext<>();
		eventContext.setEvent(event);
		Task task = new Task();
		task.setName("任务1");
		eventContext.setTask(task);
		return eventContext;
	}

	public static void consume(EventContext<Task> context) {
		context.getEvent().trigger(context);
	}

	public static void main(String[] args) {
		HelloWorldEventListener helloWorldEventListener = new HelloWorldEventListener();
		DoActualEventListener doActualEventListener = new DoActualEventListener();
//		helloWorldEventListener.register(TaskEvent.CREATE);
//		helloWorldEventListener.register(TaskEvent.ACCEPT);
//		doActualEventListener.register(TaskEvent.ACCEPT);
		EventContext<Task> eventContext = generateEvent(TaskEvent.CREATE);
		consume(eventContext);
		EventContext<Task> eventContext2 = generateEvent(TaskEvent.ACCEPT);
		consume(eventContext2);
	}

}
