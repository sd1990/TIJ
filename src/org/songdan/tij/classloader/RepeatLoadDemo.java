package org.songdan.tij.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * 使用不同的类加载器加载相同的class，类的类型是不相同的。
 * 在类加载的过程中，会加载相关类的父类或接口
 * @author Songdan
 * @date 2016/7/6
 */
public class RepeatLoadDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoaderCase classLoaderCase = new ClassLoaderCase();
        Class<?> aClass = null;
        try {
            aClass = classLoaderCase.loadClass("org.songdan.tij.classloader.RepeatLoadDemo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Object instance = aClass.newInstance();
        System.out.println("===================");
        System.out.println(instance.getClass());
        System.out.println(instance instanceof RepeatLoadDemo );
    }

}

class ClassLoaderCase extends ClassLoader{

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String fileName = name.substring(name.lastIndexOf(".")+1) + ".class";
        System.out.println(fileName);
        System.out.println("=======================");
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