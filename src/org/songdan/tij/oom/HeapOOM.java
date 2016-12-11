package org.songdan.tij.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 对内存溢出
 * Created by SongDan on 2016/10/11.
 */
public class HeapOOM {

    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> objects = new ArrayList<>();
        while (true) {
            objects.add(new OOMObject());
        }
    }

}
