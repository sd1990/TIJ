package org.songdan.tij.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 *
 * Created by PC on 2016/3/26.
 */
public class DirList2 {

    public static FilenameFilter filter(final String regex) {
        return new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return Pattern.compile(regex).matcher(name).matches();
            }
        };
    }

    public static void main(String[] args) {
        for (String name : new File(".").list(filter("D.*\\.java"))) {
            System.out.println(name);
        }
    }
}
