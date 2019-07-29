package org.songdan.tij.check.result;

import java.util.Iterator;

/**
 * 兼容failfast,failover两种模式
 * @author: Songdan
 * @create: 2019-05-14 19:46
 **/
public interface CheckResult {

    /**
     * 是否校验通过
     * @return
     */
    boolean isPass();

    /**
     * 校验结果迭代器
     * @return
     */
    Iterator<CheckResultItem> iterator();

    /**
     * 添加新的校验结果
     * @param checkResult
     * @return
     */
    boolean addResult(CheckResult checkResult);

}
