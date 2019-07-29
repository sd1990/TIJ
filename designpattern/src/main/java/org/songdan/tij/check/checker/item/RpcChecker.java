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
 * @create: 2019-07-29 11:40
 **/
public class RpcChecker implements Checker {

    static {
        CheckerRegistry.register("rpcCheck1", new RpcChecker());
        CheckerRegistry.register("rpcCheck2", new RpcChecker());
        CheckerRegistry.register("rpcCheck3", new RpcChecker());
    }

    @Override
    public CheckResult check(CheckContext checkContext) {
        int cost = ThreadLocalRandom.current().nextInt(50, 1000);
        try {
            TimeUnit.MILLISECONDS.sleep(cost);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (ThreadLocalRandom.current().nextInt(10) % 2 == 0) {
            return CheckResultItem.success(getName());
        } else {
            return CheckResultItem.fail("rpc check fail!!!");
        }

    }

    @Override
    public String getName() {
        return "rpcCheck";
    }
}
