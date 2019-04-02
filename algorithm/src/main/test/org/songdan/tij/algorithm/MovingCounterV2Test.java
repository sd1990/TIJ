package org.songdan.tij.algorithm;

import org.junit.Test;
import org.songdan.tij.algorithm.limiter.MovingCounterV2;

public class MovingCounterV2Test {

    @Test
    public void acquire() throws InterruptedException {
        MovingCounterV2 counter = new MovingCounterV2(100);
        for (int i = 0; i < 200; i++) {
            Thread.sleep(5);
            System.out.println(System.currentTimeMillis()+":"+i + ":" + counter.acquire());
        }
    }
}