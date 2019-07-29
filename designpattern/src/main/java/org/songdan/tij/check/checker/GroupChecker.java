package org.songdan.tij.check.checker;

import com.google.common.collect.Lists;
import lombok.Data;
import org.songdan.tij.check.CheckContext;
import org.songdan.tij.check.checker.support.CheckHandler;
import org.songdan.tij.check.checker.support.CheckHandlerBuilder;
import org.songdan.tij.check.result.CheckResult;

import java.util.List;

/**
 * @author: Songdan
 * @create: 2019-07-27 22:11
 **/
@Data
public class GroupChecker implements Checker {

    private boolean failfast;

    private boolean async;

    private String name;

    private List<Checker> checkerList = Lists.newArrayList();

    public boolean addChecker(Checker checker) {
        return checkerList.add(checker);
    }

    @Override
    public CheckResult check(CheckContext checkContext) {
        CheckHandler checkHandler;
        if (async) {
            checkHandler = CheckHandlerBuilder.buildAsync(checkerList, failfast);
        } else {
            checkHandler = CheckHandlerBuilder.buildSync(checkerList, failfast);
        }
        return checkHandler.check(checkContext);
    }

    @Override
    public String getName() {
        return name;
    }
}
