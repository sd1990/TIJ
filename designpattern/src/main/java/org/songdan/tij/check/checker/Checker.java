package org.songdan.tij.check.checker;

import org.songdan.tij.check.config.CheckConfigItem;
import org.songdan.tij.check.CheckContext;
import org.songdan.tij.check.result.CheckResult;

/**
 * 校验器和{@link CheckConfigItem}关联使用
 */
public interface Checker {

    CheckResult check(CheckContext checkContext);

    /**
     * 校验器的名称，CheckConfigItem.getName必须和这里的getName相同
     * @return
     */
    String getName();

}
