package org.songdan.tij.check.checker.support;

import org.songdan.tij.check.CheckContext;
import org.songdan.tij.check.checker.Checker;
import org.songdan.tij.check.result.CheckResult;
import org.songdan.tij.check.result.CheckResultGroup;

import java.util.List;

/**
 * @author: Songdan
 * @create: 2019-07-29 16:10
 **/
public class SyncCheckHandler implements CheckHandler{


    private List<? extends Checker> checkerList;

    private boolean failFast;

    public SyncCheckHandler(List<? extends Checker> checkerList, boolean failFast) {
        this.checkerList = checkerList;
        this.failFast = failFast;
    }

    @Override
    public CheckResult check(CheckContext checkContext) {
        CheckResultGroup checkResultGroup = new CheckResultGroup();
        for (final Checker checker : checkerList) {
            CheckResult checkResult = checker.check(checkContext);
            if (!checkResult.isPass()) {
                checkResultGroup.addResult(checkResult);
            }
            if (failFast) {
                return checkResultGroup;
            }
        }
        return checkResultGroup;
    }
}
