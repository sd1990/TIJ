package org.songdan.tij.algorithm.timewheel;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 时间槽
 *
 * @author: Songdan
 * @create: 2018-10-02 16:07
 **/
public class Slot implements Delayed {

    private Set<Expiration> set = new HashSet<>();

    private int point;

    private long executeTime;

    public Slot(int point) {
        this.point = point;
        this.executeTime = point*1000+System.currentTimeMillis();
    }

    public boolean add(Expiration event) {
        return set.add(event);
    }

    public boolean remove(Expiration event) {
        return set.remove(event);
    }

    public Set<Expiration> getEventList() {
        return set;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(executeTime - System.currentTimeMillis(),  TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == null) {
            return 1;
        }
        if (!(o instanceof Slot)) {
            return 1;
        }
        return (int) (this.executeTime - ((Slot) o).executeTime);
    }

    public int getPoint() {
        return point;
    }

    public static void main(String[] args) {
        System.out.println(TimeUnit.SECONDS.toMillis(1));
        System.out.println(TimeUnit.MILLISECONDS.toSeconds(1000));
    }
}
