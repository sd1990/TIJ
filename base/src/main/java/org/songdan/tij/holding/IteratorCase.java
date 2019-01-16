package org.songdan.tij.holding;

import java.util.List;

/**
 * @author: Songdan
 * @create: 2019-01-16 10:44
 **/
public class IteratorCase {

    public void nullCase() {
        List<String> list = null;
        for (String str : list) {
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        new IteratorCase().nullCase();
    }

}
