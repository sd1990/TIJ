package org.songdan.tij.check.checker.support;

import com.google.common.collect.Lists;
import org.songdan.tij.check.CheckContext;
import org.songdan.tij.check.checker.Checker;
import org.songdan.tij.check.result.CheckResult;
import org.songdan.tij.check.result.CheckResultGroup;
import org.songdan.tij.check.result.CheckResultItem;

import java.util.List;
import java.util.concurrent.*;

/**
 * @author: Songdan
 * @create: 2019-05-14 20:04
 **/
public class CheckHandlerBuilder {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
            30L, TimeUnit.SECONDS,
            new SynchronousQueue<>());

    public static CheckHandler buildSync(List<? extends Checker> checkerList, final boolean failFast) {
        CheckHandler current = checkContext -> new CheckResultGroup();
        for (final Checker checker : checkerList) {

            final CheckHandler finalCurrent = current;
            current = checkContext -> {
                CheckResult result = finalCurrent.check(checkContext);

                if (!result.isPass() && failFast) {
                    return result;
                }
                CheckResult checkResult = checker.check(checkContext);
                result.addResult(checkResult);
                return result;
            };

        }
        return current;
    }

    public static CheckHandler buildAsync(List<? extends Checker> checkerList, final boolean failFast) {
        return new CheckHandler() {
            @Override
            public CheckResult check(CheckContext checkContext) {
                if (failFast) {
                    CountDownLatch resultLatch = new CountDownLatch(1);
                    CountDownLatch checkLatch = new CountDownLatch(checkerList.size());
                    CheckResultGroup checkResultGroup = new CheckResultGroup();
                    for (Checker checker : checkerList) {
                        threadPoolExecutor.submit(new Runnable() {
                            @Override
                            public void run() {
                                CheckResult checkResult = null;
                                try {
                                    synchronized (resultLatch) {
                                        if (resultLatch.getCount() == 0) {
                                            return;
                                        }
                                    }
                                    checkResult = checker.check(checkContext);
                                } finally {
                                    checkLatch.countDown();
                                    synchronized (resultLatch) {
                                        if (resultLatch.getCount() == 1 && checkResult != null && !checkResult.isPass()) {
                                            checkResultGroup.addResult(checkResult);
                                            resultLatch.countDown();
                                        }
                                    }
                                }
                            }
                        });
                    }
                    while (true) {
                        if (resultLatch.getCount() == 0) {
                            break;
                        }
                        if (checkLatch.getCount() == 0) {
                            break;
                        }
                        try {
                            TimeUnit.MILLISECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
                        } catch (InterruptedException e) {
                            throw new RuntimeException("thread interrupted!!!");
                        }
                    }
                    return checkResultGroup;
                } else {
                    CheckResultGroup checkResultGroup = new CheckResultGroup();
                    List<Future<CheckResult>> futureList = Lists.newArrayListWithExpectedSize(checkerList.size());
                    for (Checker checker : checkerList) {
                        futureList.add(threadPoolExecutor.submit(new Callable<CheckResult>() {
                            @Override
                            public CheckResult call() throws Exception {
                                return checker.check(checkContext);
                            }
                        }));
                    }
                    List<Throwable> exceptionList = Lists.newArrayListWithExpectedSize(futureList.size());
                    for (Future<CheckResult> checkResultFuture : futureList) {
                        try {
                            checkResultGroup.addResult(checkResultFuture.get());
                        } catch (InterruptedException e) {
                            throw new RuntimeException("thread interrupted!!!");
                        } catch (ExecutionException e) {
                            exceptionList.add(e.getCause());
                        }
                    }
                    if (checkResultGroup.isPass() && exceptionList.size() > 0) {
                        checkResultGroup.addResult(CheckResultItem.fail(exceptionList.stream().map(Throwable::getMessage).toString()));
                    }
                    return checkResultGroup;
                }
            }
        };

    }

}
