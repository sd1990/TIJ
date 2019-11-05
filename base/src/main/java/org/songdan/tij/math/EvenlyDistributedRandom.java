package org.songdan.tij.math;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 均匀分布的随机
 *
 * @author: Songdan
 * @create: 2019-08-21 10:19
 **/
public class EvenlyDistributedRandom {

    public static final int HUNDRED = 100;
    private int count = 0;

    private int num = 0;

    public boolean rdm(int threshold) {
        boolean result = count < threshold;
        count = (count + 31) % HUNDRED;
        return result;
    }

    public boolean rdmV2(int threshold) {
        int i = ThreadLocalRandom.current().nextInt(HUNDRED);
        return i < threshold;
    }

    public boolean rdmV3(int threshold) {
        return (num++ % HUNDRED) < threshold;
    }

    public static void main(String[] args) {
        EvenlyDistributedRandom distributedRandom = new EvenlyDistributedRandom();
        int expectCount = 0;
        for (int i = 0; i < HUNDRED; i++) {
            int count = 0;
            for (int j = 0; j < HUNDRED; j++) {
                count += distributedRandom.rdm(31) ? 1 : 0;
            }
            if (count == 31) {
                expectCount++;
            }
        }
        System.out.println(expectCount);
        expectCount = 0;
        for (int i = 0; i < HUNDRED; i++) {
            int count = 0;
            for (int j = 0; j < HUNDRED; j++) {
                count += distributedRandom.rdmV2(31) ? 1 : 0;
            }
            if (count == 31) {
                expectCount++;
            }
        }
        System.out.println(expectCount);
        expectCount = 0;
        for (int i = 0; i < HUNDRED; i++) {
            int count = 0;
            for (int j = 0; j < HUNDRED; j++) {
                count += distributedRandom.rdmV3(31) ? 1 : 0;
            }
            if (count == 31) {
                expectCount++;
            }
        }
        System.out.println(expectCount);
    }

}
