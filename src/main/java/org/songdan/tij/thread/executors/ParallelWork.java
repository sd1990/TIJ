package org.songdan.tij.thread.executors;

import java.util.concurrent.*;

/**
 * 并行工作
 *
 * @author Songdan
 * @date 2016/8/27
 */
public class ParallelWork implements IWork {

    private final ExecutorService executor = Executors.newFixedThreadPool(8);

    @Override
    public void work() {
        long start = System.currentTimeMillis();
        CompletionService<Boolean> completionService = new ExecutorCompletionService<Boolean>(executor);
        for (int i = 0; i < 5; i++) {
            completionService.submit(new Callable<Boolean>() {

                @Override
                public Boolean call() throws Exception {
                    new Worker().work();
                    return true;
                }
            });
        }

        try {

            for (int i = 0; i < 5; i++) {
                Future<Boolean> take = completionService.take();
                System.out.println(take.get());
            }
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        catch (ExecutionException e) {
            System.out.println(e.getCause().getMessage());
        }
        System.out.println(System.currentTimeMillis() - start);
        executor.shutdown();
    }
}
