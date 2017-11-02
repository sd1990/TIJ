package org.songdan.tij.designpattern.observers;

/**
 * 事件监听接口
 *
 * @author song dan
 * @since 02 十一月 2017
 */
public interface EventListener<T> {

	void onEvent(EventContext<T> eventContext);

	boolean register(Event<T> event);

}
