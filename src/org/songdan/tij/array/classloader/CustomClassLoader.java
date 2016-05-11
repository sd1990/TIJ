package org.songdan.tij.array.classloader;

import org.songdan.tij.array.atomic.AtomicIntegerDemo;

import java.io.*;

public class CustomClassLoader extends ClassLoader {

    private static String HOME=CustomClassLoader.class.getClassLoader().getResource("").getPath();
    
    private String name;
    
    private static final String CLASS_TYPE=".class"; 
    
    private String path;
    
    public CustomClassLoader(String name) {
        super();
        this.name = name;
    }

    
    public CustomClassLoader(String name,ClassLoader parent) {
        super(parent);
        this.name = name;
    }


    
    
    public String getPath() {
        return path;
    }


    
    public void setPath(String path) {
        this.path = path;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData=getClassData(name);
        if(classData==null){
            throw new ClassNotFoundException();
        }else{
            Class<?> defineClass = defineClass(name, classData, 0, classData.length);
            return defineClass;
        }
    }

    /**
     * 根据类名获取字节码的字节数组
     * @param name
     * @return
     */
    private byte[] getClassData(String name) {
        String path = className2Path(name);
        try{
            InputStream input = new FileInputStream(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes=new byte[4096];
            int bytesOutRead=0;
            while((bytesOutRead=input.read(bytes))!=-1){
                baos.write(bytes,0,bytesOutRead);
            }
            return baos.toByteArray();
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }

    private String className2Path(String name) {
        return path+ File.separator+name.replace(".", File.separator)+".class";
    }
    
    
    
    @Override
    public String toString() {
        return name;
    }
    
    public static void traverseParent(ClassLoader loader) throws Exception{
        if(loader == null) return;
        System.out.println("travase classloader:" + loader.toString());
        while(loader.getParent() != null){
            System.out.println(loader.getParent());
            loader = loader.getParent();
        }
    }
 
    public static void test(ClassLoader loader) throws Exception {
        Class<?> clazz = loader.loadClass("com.songdan.demo.atomic.AtomicIntegerDemo");
        Object object = clazz.newInstance();
    }
 
    public static void testNameSpace(ClassLoader loader) throws Exception {
        Class<?> clazz = loader.loadClass("com.songdan.demo.atomic.AtomicIntegerDemo");
        Object object = clazz.newInstance();
        try{
            AtomicIntegerDemo lc = (AtomicIntegerDemo) object;
        }catch(Exception e){
            e.printStackTrace();
        }
    } 

    public static void main(String[] args) throws Exception {
        CustomClassLoader father = new CustomClassLoader("father",null);
        father.setPath(HOME+"");
        
        
        CustomClassLoader child = new CustomClassLoader("child", father);
        child.setPath(HOME+"ext\\");

        CustomClassLoader user = new CustomClassLoader("user",null);
        user.setPath(HOME+"");
        System.out.println("test parent ==========================");
        traverseParent(child);
        System.out.println("test load begin from child ==========================");
        test(child);
        System.out.println("====================================");
        testNameSpace(user);
    }
}
