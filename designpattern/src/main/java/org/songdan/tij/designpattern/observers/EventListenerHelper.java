package org.songdan.tij.designpattern.observers;

import javax.annotation.PostConstruct;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 03 十一月 2017
 */
public abstract class EventListenerHelper<T> implements EventListener<T> {

	@PostConstruct
	public void init() {
		initRegister();
	}
	@Override
	public boolean register(Event<T> event) {
		return event.register(this);
	}

	protected abstract void initRegister();
}
