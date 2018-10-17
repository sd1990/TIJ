package org.songdan.tij.algorithm.timewheel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author: Songdan
 * @create: 2018-10-03 16:47
 **/
public class ExpirationStore {

    private List<Expiration> store = new ArrayList<>();

    public boolean save(Expiration event) {
        return store.add(event);
    }

    public List<Expiration> getRange(long leftClose, long maxOpen) {
        Collections.sort(store);
        int leftCloseIndex = -1;
        long temp = leftClose;
        long first = leftClose;
        do {
            long finalTemp = temp;
            if (temp==maxOpen) {
                break;
            }
            leftCloseIndex = Collections.binarySearch(store, new Expiration() {
                @Override
                public int compareTo(Expiration o) {
                    return (int) (this.getExpireTime() - o.getExpireTime());
                }

                @Override
                public void expired() {

                }

                @Override
                public long getExpireTime() {
                    return finalTemp;
                }
            });
            first = finalTemp;
            temp++;
        } while (leftCloseIndex==-1);
        if (leftCloseIndex==-1) {
            return new ArrayList<>();
        }
        while (leftCloseIndex>0) {
            if (store.get(leftCloseIndex - 1).getExpireTime()!=first) {
                break;
            }
            leftCloseIndex--;
        }
        List<Expiration> result = new ArrayList<>();

        for (int i = leftCloseIndex; i < store.size(); i++) {
            Expiration expiration = store.get(i);
            if (expiration.getExpireTime() < maxOpen) {
                result.add(expiration);
                store.remove(i);
                i--;
            }
        }
        return result;
    }

}
