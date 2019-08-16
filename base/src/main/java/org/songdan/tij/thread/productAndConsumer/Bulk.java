package org.songdan.tij.thread.productAndConsumer;


import java.util.List;

/**
 * @author: Songdan
 * @create: 2019-08-16 11:07
 **/
public class Bulk {

    private List<Long> list;

    public List<Long> getList() {
        return list;
    }

    public void setList(List<Long> list) {
        this.list = list;
    }

    public Bulk(List<Long> list) {
        this.list = list;
    }

    public static class PoisonBulk extends Bulk {

        public PoisonBulk() {
            super(null);
        }

        public PoisonBulk(List<Long> list) {
            super(list);
        }
    }
}
