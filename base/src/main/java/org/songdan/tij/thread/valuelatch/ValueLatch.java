package org.songdan.tij.thread.valuelatch;

import java.util.concurrent.CountDownLatch;

/**
 * 参考Concurrent In Practice，并行寻找答案
 * @author: Songdan
 * @create: 2018-08-08 14:33
 **/
public class ValueLatch<T extends Satisfiable> {

    private T value;

    private Exception e;

    private final CountDownLatch done = new CountDownLatch(1);

    public boolean isSet() {
        return done.getCount() == 0;
    }

    public synchronized void setValue(T value) {
        if (!isSet()) {
            this.value = value;
            done.countDown();
        }
    }

    public synchronized void setException(Exception e) {
        if (!isSet()) {
            this.e = e;
            done.countDown();
        }
    }

    public T getValue() throws Exception {
        done.await();
        synchronized (this) {
            if (e != null) {
                throw e;
            }
            return value;
        }
    }

}


