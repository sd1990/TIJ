package org.songdan.tij.check.checker.support;

import org.songdan.tij.check.CheckContext;
import org.songdan.tij.check.result.CheckResult;

/**
 * 处理校验的类
 */
public interface CheckHandler {

    CheckResult check(CheckContext checkContext);

}
