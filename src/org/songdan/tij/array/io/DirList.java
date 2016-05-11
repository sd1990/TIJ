package org.songdan.tij.array.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * Created by PC on 2016/3/26.
 */
public class DirList {

    public static void main(String[] args) {
        File path = new File(".");
        String[] list = path.list(new DirFilter("D.*\\.java"));
        for (String s : list) {
            System.out.println(s);
        }
    }
}

/**
 * 创建一个类实现FilenameFilter,策略模式的体现
 */
class DirFilter implements FilenameFilter{

    private Pattern pattern ;

    public DirFilter(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
