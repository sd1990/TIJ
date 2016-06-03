package org.songdan.tij.effective_java.item25;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2016/4/25.
 */
public class ArrayVsGeneric {

    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        Object[] obj = new Object[10];
        obj[0] = stringList;
        obj[1] = new ArrayList<Integer>();
    }

}
