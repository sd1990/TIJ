package org.songdan.tij.thread.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author Songdan
 * @date 2017/5/18 17:19
 */
public class ForkJoinDemo {

    public void work() {
        //根据cpu核数生成有多少可以并行处理的线程
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long start = System.currentTimeMillis();
        Task task = new Task(0,1024);
        forkJoinPool.invoke(task);
        System.out.println(System.currentTimeMillis() - start);
    }

    class Task extends RecursiveAction {

        private int start;

        private int end;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start > 20) {
                //分解任务
                List<Task> subTasks = forkTasks();
                invokeAll(subTasks);
            } else {
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + " :workLoad is : [" + start + "-" + end + "]");
            }
        }

        private List<Task> forkTasks() {
            ArrayList<Task> tasks = new ArrayList<>();
            int mid = (start+end) / 2;
//            System.out.println(Thread.currentThread() + " :"+mid);
            tasks.add(new Task(start, mid));
            tasks.add(new Task(mid +1, this.end));
            return tasks;
        }
    }

    class TaskV2 extends RecursiveAction {

        private long workLoad;

        public TaskV2(long workLoad) {
            this.workLoad = workLoad;
        }

        @Override
        protected void compute() {
            if (workLoad <= 20) {
                System.out.println(Thread.currentThread() + " :workLoad is :" + workLoad);
            } else {
                List<TaskV2> subTasks = forkTasks();
                invokeAll(subTasks);
            }
        }

        private List<TaskV2> forkTasks() {
            ArrayList<TaskV2> tasks = new ArrayList<>();
            for (int i = 0; i < workLoad; ) {
                i = i + 20;
                tasks.add(new TaskV2(20));
            }
            return tasks;
        }
    }

    public static void main(String[] args) {
        new ForkJoinDemo().work();
    }

}
