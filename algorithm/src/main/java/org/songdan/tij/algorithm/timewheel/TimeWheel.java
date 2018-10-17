package org.songdan.tij.algorithm.timewheel;

/**
 * 时间轮
 *
 * @author: Songdan
 * @create: 2018-10-02 16:07
 **/
public class TimeWheel {


    private ExpirationStore store = new ExpirationStore();

    private Clocker clocker = new Clocker(store);

    public boolean add(Expiration event) {
        double ceil = Math.floor(event.getExpireTime() / 1d);
        if (ceil>=Clocker.INTERVAL) {
            return store.save(event);
        }
        Slot slot = clocker.getSlot((int) ceil);
        if (slot != null) {
            return slot.add(event);
        }
        return false;
    }

    public boolean remove(Expiration event) {
        double ceil = Math.ceil(event.getExpireTime() / 1000d);
        Slot slot = clocker.getSlot((int) ceil);
        return slot.remove(event);
    }

}
