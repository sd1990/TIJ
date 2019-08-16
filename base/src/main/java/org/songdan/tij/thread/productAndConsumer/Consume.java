package org.songdan.tij.thread.productAndConsumer;

public interface Consume<P,R> {

    R consume(P param);

}
