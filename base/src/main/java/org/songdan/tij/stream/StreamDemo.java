package org.songdan.tij.stream;

import java.util.ArrayList;
import java.util.Optional;

/**
 * TODO completion javadoc.
 *
 * @author song dan
 * @since 26 二月 2018
 */
public class StreamDemo {

    public static void findFirsrt() {
        ArrayList<Object> list = new ArrayList<>();
        list.add(null);
        Optional<Object> first = list.stream().findFirst();
    }

    public static void main(String[] args) {
        findFirsrt();
    }

}
