package org.songdan.tij.io;

import java.io.File;

/**
 * @author Songdan
 * @date 2016/5/19
 */
public class FileDemo {

    public static void testPath() {
        String absolutePath = new File("/").getAbsolutePath();
        System.out.println(absolutePath);
    }

    public static void main(String[] args) {
        testPath();
    }

}
