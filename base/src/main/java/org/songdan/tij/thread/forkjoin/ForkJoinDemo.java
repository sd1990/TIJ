package org.songdan.tij.thread.forkjoin;

import java.util.ArrayList;
import java.util.List;
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
        forkJoinPool.invoke(new Task(1024));
        try {
            TimeUnit.SECONDS.sleep(10);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class Task extends RecursiveAction {

        private long workLoad;

        public Task(long workLoad) {
            this.workLoad = workLoad;
        }

        @Override
        protected void compute() {
            if (workLoad > 500) {
                //分解任务
                List<Task> subTasks= forkTasks();
                for (Task subTask : subTasks) {
                    subTask.fork();
                }
            }else{
                System.out.println(Thread.currentThread()+" :workLoad is :"+workLoad);
            }
        }

        private List<Task> forkTasks() {
            ArrayList<Task> tasks = new ArrayList<>();
            tasks.add(new Task(workLoad / 2));
            tasks.add(new Task(workLoad / 2));
            return tasks;
        }
    }

    public static void main(String[] args) {
        new ForkJoinDemo().work();
    }

}
