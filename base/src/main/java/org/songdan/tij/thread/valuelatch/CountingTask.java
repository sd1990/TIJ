package org.songdan.tij.thread.valuelatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 如果能找到答案，设置valueLatch结果，如果计数器清零还没有找到答案，valueLatch设置为null
 *
 * @author: Songdan
 * @create: 2018-08-08 14:59
 **/
public class CountingTask<T extends Satisfiable> implements Runnable {

    private Callable<T> work;

    private AtomicInteger count;

    private ValueLatch<T> solution;

    public CountingTask(Callable<T> call, AtomicInteger count, ValueLatch<T> valueLatch) {
        this.work = call;
        this.count = count;
        this.solution = valueLatch;
    }

    @Override
    public void run() {
        if (solution.isSet()) {
            return;
        }
        try {
            T value = work.call();
            if (value.isSatisfy()) {
                solution.setValue(value);
            }
        } catch (Exception e) {
            solution.setException(e);
        } finally {
            if (count.decrementAndGet() == 0) {
                if (!solution.isSet()) {
                    solution.setValue(null);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ValueLatch<ApplyCondition> applyConditionValueLatch = new ValueLatch<>();
        List<CountingTask<ApplyCondition>> list = new ArrayList<>();
        long start = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(10);
        for (int i = 0; i < 10; i++) {
            list.add(new CountingTask<>(new Callable<ApplyCondition>() {
                @Override
                public ApplyCondition call() throws Exception {
                    System.out.println(Thread.currentThread()+": working !!!");
//                    if (ThreadLocalRandom.current().nextInt()%2 == 0) {
//                        throw new RuntimeException("wo shi gu yi 的");
//                    }
                    TimeUnit.
                            SECONDS.sleep(2);
                    return new ApplyCondition();
                }
            }, count, applyConditionValueLatch));
        }
        for (CountingTask<ApplyCondition> applyConditionCountingTask : list) {
            executorService.execute(applyConditionCountingTask);
        }
        System.out.println(applyConditionValueLatch.getValue());
        System.out.println(System.currentTimeMillis()-start);
        executorService.shutdownNow();

    }
}
