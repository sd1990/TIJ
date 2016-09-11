package org.songdan.tij.thread.evenchecker;

import org.songdan.tij.thread.countdownlatch.CountDownTime;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 检测每次生成的数字是否都是偶数
 * Created by PC on 2016/5/14.
 */
public class EvenChecker implements Runnable {

    private IntGenerator intGenerator;

    public EvenChecker(IntGenerator intGenerator) {
        this.intGenerator = intGenerator;
    }

    @Override
    public void run() {
        while (!intGenerator.isCanceled()) {
            int val = intGenerator.next();
            System.out.println(Thread.currentThread().getName()+"生成的数字是："+val);
            if (val % 2 != 0) {
                System.out.println(Thread.currentThread().getName()+"坏了，出问题了，生成的数字"+val+"不是偶数！");
                intGenerator.cancel();
            }
        }
    }

    public static void test(IntGenerator intGenerator,int concurrent) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        long runtime = CountDownTime.time(executorService, concurrent, new EvenChecker(intGenerator));
        System.out.println("用时"+runtime+"纳秒");
        /*for (int i = 0; i < concurrent; i++) {
            executorService.execute(new EvenChecker(intGenerator));
        }*/
//        executorService.shutdown();
    }

    public static void test(IntGenerator intGenerator) throws InterruptedException {
        test(intGenerator,10);
    }
}
