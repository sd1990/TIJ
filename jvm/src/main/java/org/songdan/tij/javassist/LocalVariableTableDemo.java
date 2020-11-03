package org.songdan.tij.javassist;

import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

/**
 * @author: Songdan
 * @create: 2020-09-01 13:20
 **/
public class LocalVariableTableDemo {

    public void m1(String var1, String var2) {

    }

    public void m2(long var1,int var2, int var3) {

    }

    public void m3(LocalVariableTableDemo tableDemo,int var2, int var3) {

    }

    public static void main(String[] args) throws NotFoundException {
        LocalVariableTableDemo localVariableTableDemo = new LocalVariableTableDemo();
        getParameterNamesNoCache(LocalVariableTableDemo.class, "m1");
        getParameterNamesNoCache(LocalVariableTableDemo.class, "m2");
        getParameterNamesNoCache(LocalVariableTableDemo.class, "m3");
    }

    private static String[] getParameterNamesNoCache(Class cls, String methodName) throws NotFoundException {
        String[] paramNames = null;
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);
        CtClass cc = pool.get(cls.getName());
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        System.out.println(attr);
        if (attr != null) {
            paramNames = new String[cm.getParameterTypes().length];
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < paramNames.length; i++) {
                System.out.println("param name:" + attr.variableName(i + pos));
                System.out.println("param slot:" + attr.index(i));
            }
        }
        return paramNames;
    }

}
