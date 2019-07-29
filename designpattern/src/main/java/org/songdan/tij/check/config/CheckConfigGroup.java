package org.songdan.tij.check.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.songdan.tij.check.checker.Checker;
import org.songdan.tij.check.checker.GroupChecker;

import java.util.Comparator;
import java.util.List;

/**
 * 任务创建时校验配置
 */
@Data
@NoArgsConstructor
@JsonDeserialize(as = CheckConfigGroup.class)
public class CheckConfigGroup extends CheckConfig {
    private List<CheckConfig> checkConfigList = Lists.newArrayList();
    /**
     * 是否快速失败，即一个校验失败，后面的都不走
     */
    private boolean failFast;

    private boolean async;

    private int order;


    public boolean addCheckConfig(CheckConfigItem checkConfig) {
        return checkConfigList.add(checkConfig);
    }

    @Override
    public Checker getChecker() {
        GroupChecker groupChecker = new GroupChecker();
        groupChecker.setFailfast(failFast);
        groupChecker.setAsync(async);
        checkConfigList.sort(Comparator.comparingInt(Ordered::getOrder));
        for (CheckConfig checkConfig : checkConfigList) {
            groupChecker.addChecker(checkConfig.getChecker());
        }
        return groupChecker;
    }
}
