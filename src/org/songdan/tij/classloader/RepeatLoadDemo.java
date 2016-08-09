package org.songdan.tij.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Songdan
 * @date 2016/7/6
 */
public class RepeatLoadDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoaderCase classLoaderCase = new ClassLoaderCase();
        classLoaderCase.loadClass("org.songdan.tij.classloader.RepeatLoadDemo");
        classLoaderCase.loadClass("org.songdan.tij.classloader.RepeatLoadDemo");
    }

}

class ClassLoaderCase extends ClassLoader{

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(0, name.lastIndexOf(".")) + ".class";
        System.out.println(fileName);
        InputStream in = getClass().getResourceAsStream(fileName);
        if (in==null) {
            return super.loadClass(name);
        }
        try {
            byte[] bys = new byte[in.available()];
            in.read(bys);
            return defineClass(name, bys, 0, bys.length);
        } catch (IOException e) {
            throw new ClassNotFoundException();
        }
    }
}