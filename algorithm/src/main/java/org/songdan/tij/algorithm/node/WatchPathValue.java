package org.songdan.tij.algorithm.node;

import java.io.Serializable;
import java.util.List;

public class WatchPathValue implements Serializable {

    // path of the list being watched
    private String watchPath;

    // key: diff type
    // value: difference in one type
    private List<DiffValue> watchValues;

    public String getWatchPath() {
        return watchPath;
    }

    public void setWatchPath(String watchPath) {
        this.watchPath = watchPath;
    }

    public List<DiffValue> getWatchValues() {
        return watchValues;
    }

    public void setWatchValues(List<DiffValue> watchValues) {
        this.watchValues = watchValues;
    }

}
