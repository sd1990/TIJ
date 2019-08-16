package org.songdan.tij.thread.productAndConsumer;

import java.util.List;

/**
 * @author: Songdan
 * @create: 2019-08-16 10:33
 **/
class ProgressRateConsumer implements Runnable {

    private ConsumeProgressRate consumeProgressRate;

    private Consume<Bulk, Boolean> consume;

    private Bulk bulk;

    public ProgressRateConsumer(ConsumeProgressRate consumeProgressRate, Consume<Bulk, Boolean> consume, Bulk bulk) {
        this.consumeProgressRate = consumeProgressRate;
        this.consume = consume;
        this.bulk = bulk;
    }

    @Override
    public void run() {
        List<Long> list = bulk.getList();
        if (list == null || list.size() == 0) {
            return;
        }
        if (consume.consume(bulk)) {
            Long offset = consumeProgressRate.remove(list);
            if (offset > 0) {
                consumeProgressRate.storeOffSet(offset);
            }
        }
    }

}
