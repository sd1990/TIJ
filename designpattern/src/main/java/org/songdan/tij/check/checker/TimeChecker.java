package org.songdan.tij.check.checker;

import com.google.common.base.Stopwatch;
import org.songdan.tij.check.CheckContext;
import org.songdan.tij.check.result.CheckResult;

import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2019-07-29 11:59
 **/
public class TimeChecker implements Checker {

    private Checker checker;


    public TimeChecker(Checker checker) {
        this.checker = checker;
    }

    @Override
    public CheckResult check(CheckContext checkContext) {
        Stopwatch started = Stopwatch.createStarted();
        CheckResult checkResult = checker.check(checkContext);
        long elapsed = started.elapsed(TimeUnit.MICROSECONDS);
        System.out.println(checker.getName() + " cost:" + elapsed + " micro seconds");
        return checkResult;
    }

    @Override
    public String getName() {
        return checker.getName();
    }
}
