package org.songdan.tij.throttle;

public interface Throttlable {

    /**
     * 相同的identify task视为同类task，对同类任务会节流
     * @return
     */
    String identify();

    /**
     * 节流窗口期
     * @return
     */
    long period();

}
