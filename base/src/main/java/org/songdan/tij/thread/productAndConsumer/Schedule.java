package org.songdan.tij.thread.productAndConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Schedule {

    public void execute() throws InterruptedException {
        //从缓存中获取，如果缓存中没有，默认使用-1
        long startId = getStartPoiId();
        long maxId = getMaxPoiId();
        ConsumeProgressRate consumeProgressRate = new ConsumeProgressRate();
        boolean hasInterrupt = false;
        int consumerNum = Runtime.getRuntime().availableProcessors();
        System.out.println("thread num:" + consumerNum);
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(consumerNum);
        while (startId < maxId) {
            consumeProgressRate.showRate();
            List<Long> idList = getPagedWmPoiIdList(startId);
            consumeProgressRate.append(idList);
            List<List<Long>> partition = new ArrayList<>();
            for (int i = 0; i < idList.size(); ) {
                List<Long> p = new ArrayList<>();
                for (int j = 0; j < 10 && i < idList.size(); j++) {
                    p.add(idList.get(i));
                    i++;
                }
                partition.add(p);
            }
            for (List<Long> list : partition) {
                executorService.submit(new ProgressRateConsumer(consumeProgressRate, param -> migrate(param.getList()), new Bulk(list)));
            }
            startId = idList.get(idList.size() - 1);
            TimeUnit.MILLISECONDS.sleep(10);
        }
        executorService.shutdown();
        try {
            while (!executorService.awaitTermination(1, TimeUnit.SECONDS)) {
                consumeProgressRate.showRate();
            }
            consumeProgressRate.showRate();
        } catch (Exception e) {
            System.out.println("thread pool wait termination fail due to :" + e.getMessage());
        }
    }

    private List<Long> getPagedWmPoiIdList(long startId) {
        ArrayList<Long> idList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            startId = startId + random.nextInt(10) + 1;
            idList.add(startId);
        }
        return idList;
    }

    private long getMaxPoiId() {
        return 7655037;
    }

    private long getStartPoiId() {
        return 0;
    }

    private Boolean migrate(List<Long> list) {
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException {
        new Schedule().execute();
    }


}
