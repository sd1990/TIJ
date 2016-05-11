package org.songdan.tij.array.classloader;

import java.io.*;

public class CppherUtils {
    public static void cypher(InputStream input,OutputStream out) throws IOException{
        int b=0;
        while((b=input.read())!=-1){
            out.write(b^0xff);
        }
    }
    
    public static void main(String[] args) throws Exception {
        InputStream in = new FileInputStream(new File("D:\\workplace\\songdanDemo\\target\\classes\\com\\songdan\\demo\\atomic\\AtomicIntegerDemo.class"));
        OutputStream out = new FileOutputStream("D:\\workplace\\songdanDemo\\testlib\\com\\songdan\\demo\\atomic\\AtomicIntegerDemo.class");
        cypher(in, out);
        in.close();
        out.close();
//        encryDir(new File("D:\\workplace\\songdanDemo\\target\\classes"));
    }
    private static String className2Path(String name) {
        return CppherUtils.class.getClassLoader().getResource("").getPath()+name.replace(".", File.separator)+".java";
    }
    
    public static void encryDir(File srcDir) throws Exception{
        File[] listFiles = srcDir.listFiles();
        for (File file : listFiles) {
            if(file.isDirectory()){
                encryDir(file);
            }else{
                if(file.getName().endsWith(".class")){
                    String pPath = file.getParentFile().getPath();
                    String desPath=pPath.replace("target\\classes", "testlib");
                    File pDir = new File(desPath);
                    if(!pDir.exists()){
                        pDir.mkdirs();
                    }
                    File f=new File(file.getAbsolutePath().replace("target\\classes", "testlib"));
                    cypher(new FileInputStream(file), new FileOutputStream(f));
                }
            }
        }
    }
}
