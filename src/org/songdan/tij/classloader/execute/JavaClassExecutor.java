package org.songdan.tij.classloader.execute;

import java.lang.reflect.Method;

/**
 * JavaClass执行工具
 * 
 * @author Songdan
 * @date 2016/11/3 11:08
 */
public class JavaClassExecutor {

    public static String execute(byte[] bytes) {
        HackSystem.clearBuffer();
        ClassModifier classModifier = new ClassModifier(bytes);
        byte[] modifyBytes = classModifier.modifyUTF8Constant("java/lang/System",
                "org/songdan/tij/classloader/execute/HackSystem");
        HotSwapClassloader hotSwapClassloader = new HotSwapClassloader();
        Class clazz = hotSwapClassloader.loadByte(modifyBytes);
        Method method = null;
        try {
            method = clazz.getMethod("main", new Class[] { String[].class });
            method.invoke(null, new String[] { null });
        }
        catch (Throwable e) {
            e.printStackTrace();
        }
        return HackSystem.getBufferString();
    }
}
