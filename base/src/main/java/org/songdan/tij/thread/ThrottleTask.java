package org.songdan.tij.thread;

import java.util.concurrent.Delayed;

/**
 * 具备标示的task
 */
public interface ThrottleTask extends Delayed {
    /**
     * 任务的识别，相同的identify task可以节流
     * @return
     */
    String identify();
}
