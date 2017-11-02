package org.songdan.tij.designpattern.observers;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 02 十一月 2017
 */
public abstract class AbstractEvent<T> implements Event<T> {

	private List<EventListener<T>> observers = new ArrayList<>();

	protected List<EventListener<T>> getObservers() {
		return this.observers;
	}

	@Override
	public boolean register(EventListener<T> listener) {
		return observers.add(listener);
	}

	@Override
	public void trigger(EventContext<T> context) {

		getObservers().forEach(listener->{
			listener.onEvent(context);
		});

	}
}
