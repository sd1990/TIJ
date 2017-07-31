package org.songdan.tij.thread.executors;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 任务调度
 * @author Songdan
 * @date 2017/7/31 14:45
 */
public class ScheduleExecutor {


    private ScheduledExecutorService taskExecutorService;

    private Map<String,ScheduledFuture<?>> scheduledMap = new HashMap<>();

    public ScheduleExecutor(ScheduledExecutorService taskExecutorService) {
        this.taskExecutorService = taskExecutorService;

    }

    public boolean addTask(CustomTask task) {

        ScheduledFuture<?> scheduledFuture = taskExecutorService.scheduleAtFixedRate(task, 1, 3, TimeUnit.SECONDS);
        scheduledMap.put(task.getId(), scheduledFuture);
        return true;
    }

    public static void main(String[] args) {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(5);
        //这个地方需要设置取消删除策略，否则定时任务取消，schedule不会把task移除，这只会确保isDone方法一直返回true，可能会导致内存泄漏
        poolExecutor.setRemoveOnCancelPolicy(true);
        ScheduleExecutor scheduleExecutor = new ScheduleExecutor(poolExecutor);
        CustomTask customTask = new CustomTask(scheduleExecutor);
        boolean b = scheduleExecutor.addTask(customTask);
    }

    private static class CustomTask implements Runnable {

        private String id;

        private int times;

        private ScheduleExecutor scheduleExecutor;

        public int getTimes() {
            return times;
        }

        public void setTimes(int times) {
            this.times = times;
        }

        public CustomTask(ScheduleExecutor scheduleExecutor) {
            this.scheduleExecutor = scheduleExecutor;
        }

        @Override
        public void run() {
            if (times >= 3) {
                System.out.println("over ...");
                ScheduledFuture<?> scheduledFuture = scheduleExecutor.scheduledMap.get(id);
                scheduledFuture.cancel(false);
            }else{
                System.out.println("hello canceled schedule");
                times++;
            }
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public ScheduleExecutor getScheduleExecutor() {
            return scheduleExecutor;
        }

        public void setScheduleExecutor(ScheduleExecutor scheduleExecutor) {
            this.scheduleExecutor = scheduleExecutor;
        }
    }

}
