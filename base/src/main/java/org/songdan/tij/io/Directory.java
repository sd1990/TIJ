package org.songdan.tij.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

/**
 * File Directory Util
 * final means can't be extended
 * Created by PC on 2016/3/26.
 */
public final class Directory {

    /**
     * 产生当前有本地目录中文件构成的File对象数组
     *
     * @param dir   指定的本地目录
     * @param regex 文件名需要匹配的正则表达式
     * @return 指定目录下满足条件的文件对象数组
     */
    public static File[] local(File dir, String regex) {
        return null;
    }

    /**
     * 重载的local方法
     *
     * @param path
     * @param regex
     * @return
     */
    public static File[] local(String path, String regex) {
        return local(new File(path), regex);
    }

    /**
     * 用来存储文件的对象，实现了Iterable接口，返回文件Iterator
     */
    public static class TreeInfo implements Iterable<File> {

        /**
         * File对象容器
         */
        public List<File> files = new ArrayList<>();

        /**
         * File Dir 容器
         */
        public List<File> dirs = new ArrayList<>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo others) {
            files.addAll(others.files);
            dirs.addAll(others.dirs);
        }

        @Override
        public String toString() {
            return "dirs: " + dirs.toString() + "\n\nfiles: " + files.toString();
        }
    }

    public static TreeInfo walk(File start, String regex) {
        return recurseDir(start, regex);
    }

    public static TreeInfo walk(String start, String regex) {
        return walk(new File(start), regex);
    }

    public static TreeInfo walk(File start) {
        return walk(start, ".*");
    }

    public static TreeInfo walk(String start) {
        return walk(new File(start));
    }

    /**
     * 递归遍历指定目录下所有的匹配文件（包含子目录）
     *
     * @param start 父目录
     * @param regex 匹配条件
     * @return 封装好的文件对象
     */
    private static TreeInfo recurseDir(File start, String regex) {
        TreeInfo treeInfo = new TreeInfo();
        for (File file : start.listFiles()) {
            if (file.isFile()) {
                if (Pattern.compile(regex).matcher(file.getName()).matches())
                    treeInfo.files.add(file);
            }
            else {
                treeInfo.dirs.add(file);
                //递归遍历当前目录
                treeInfo.addAll(recurseDir(file, regex));
            }
        }
        return treeInfo;
    }

    public static void main(String[] args) {
        System.out.println(walk("."));
    }
}
