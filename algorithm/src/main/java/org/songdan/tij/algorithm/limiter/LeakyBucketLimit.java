package org.songdan.tij.algorithm.limiter;

/**
 * 漏桶限流,漏桶中的水以一定的速率流出漏桶，当有水流入到漏桶中时，如果漏桶已满，则抛弃流入的水
 * @author: Songdan
 * @create: 2019-02-26 15:04
 **/
public class LeakyBucketLimit {

    private double rate; //leak rate in calls/s

    private double burst;

    private double water;//漏桶中已有的水

    private long refreshTime;

    private void refreshWater() {
        long now = System.currentTimeMillis() / 1000;
        //water-rate*(上一次到现在流逝的时间)
        water = Math.max(0,water - (rate * (now - refreshTime)));
        refreshTime = now;
    }

    public boolean permitGurant() {
        refreshWater();
        if (water<burst) {
            //外部的水流入到漏桶中
            water++;
            return true;
        }
        return false;
    }

}
