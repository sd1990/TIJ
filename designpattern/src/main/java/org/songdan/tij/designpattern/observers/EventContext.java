package org.songdan.tij.designpattern.observers;

import lombok.Data;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 02 十一月 2017
 */
@Data
public class EventContext<T> {

	private Event<T> event;

	private T task;

}
