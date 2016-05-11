package org.songdan.tij.array.io.practice;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * 练习题1
 * Created by PC on 2016/3/26.
 */
public class Test2 {

}

class SortedDirList {

    private File file;

    public SortedDirList(String path) {
        file = new File(path);
    }

    public String[] list() {
        String[] list = file.list();
        Arrays.sort(list,String.CASE_INSENSITIVE_ORDER);
        return list;
    }

    public String[] list(final String regex) {
        return file.list(new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                return Pattern.compile(regex).matcher(name).matches();
            }
        });
    }
}





