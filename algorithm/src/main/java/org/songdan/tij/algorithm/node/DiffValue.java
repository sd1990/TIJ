/*
 * Copyright (c) 2014 Qunar.com. All Rights Reserved.
 */

package org.songdan.tij.algorithm.node;

import java.io.Serializable;

public class DiffValue implements Comparable<DiffValue>, Serializable {

    private static final long serialVersionUID = -4776692545499634377L;

    private DiffType diffType;

    private String path;

    private ValueNode oldValue;

    private ValueNode newValue;

    private DiffValue() {
    }

    public DiffValue(DiffType diffType, String path, ValueNode oldValue, ValueNode newValue) {
        this.diffType = diffType;
        this.path = path;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public String toString() {
        return "DiffValue{" + "diffType=" + diffType + ", path=" + path + ", oldValue=" + oldValue + ", newValue="
                + newValue + '}';
    }

    @Override
    public int compareTo(DiffValue o) {
        int i = path.compareTo(o.path);
        if (i != 0)
            return i;
        if (this.diffType == o.diffType)
            return 0;
        return this.diffType.compareTo(o.diffType);
    }

    public DiffType getDiffType() {
        return diffType;
    }

    public void setDiffType(DiffType diffType) {
        this.diffType = diffType;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public ValueNode getOldValue() {
        return oldValue;
    }

    public void setOldValue(ValueNode oldValue) {
        this.oldValue = oldValue;
    }

    public ValueNode getNewValue() {
        return newValue;
    }

    public void setNewValue(ValueNode newValue) {
        this.newValue = newValue;
    }

    /**
     * Created by caohanzhen on 17/1/11.
     */
    public static enum DiffType {
        DELETE, // 删除节点
        CHANGE, // 节点数据变更
        ADD, // 新增节点
        UNCHANGED;//未更新
    }
}
