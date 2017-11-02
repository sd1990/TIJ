package org.songdan.tij.designpattern.observers;


/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 02 十一月 2017
 */
public interface Event<T> {



	boolean register(EventListener<T> listener);

	void trigger(EventContext<T> context);



}
