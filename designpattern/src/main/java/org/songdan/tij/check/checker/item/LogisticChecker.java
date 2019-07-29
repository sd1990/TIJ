package org.songdan.tij.check.checker.item;

import org.songdan.tij.check.CheckContext;
import org.songdan.tij.check.CheckerRegistry;
import org.songdan.tij.check.checker.Checker;
import org.songdan.tij.check.result.CheckResult;
import org.songdan.tij.check.result.CheckResultItem;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2019-07-29 11:44
 **/
public class LogisticChecker implements Checker {

    static {
        CheckerRegistry.register("logisticCheck1", new LogisticChecker());
        CheckerRegistry.register("logisticCheck2", new LogisticChecker());
        CheckerRegistry.register("logisticCheck3", new LogisticChecker());
    }

    @Override
    public CheckResult check(CheckContext checkContext) {
        int cost = ThreadLocalRandom.current().nextInt(1, 10);
        try {
            TimeUnit.MILLISECONDS.sleep(cost);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if ((cost & 1) == 0) {
            return CheckResultItem.success(getName());
        } else {
            return CheckResultItem.fail("logistic check fail!!!");
        }
    }

    @Override
    public String getName() {
        return "logisticCheck";
    }
}
