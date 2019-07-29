package org.songdan.tij.check.config;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.songdan.tij.check.CheckerRegistry;
import org.songdan.tij.check.checker.Checker;

/**
 * 校验配置
 */
@ToString
@EqualsAndHashCode
@JsonDeserialize(as = CheckConfigItem.class)
public class CheckConfigItem extends CheckConfig {
    private boolean open;

    private String name;

    private String message;

    private int order;

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public Checker getChecker() {
        return CheckerRegistry.getByName(getName());
    }
}
