package org.songdan.tij.classloader.execute;

/**
 * 为了实现多次载入执行类而加入的加载器
 * 把defineClass方法开放出来，只有显示外部调用的时候才会使用到loadByte方法。
 * 虚拟机执行时，仍然按照原有的双亲委派规则使用loadClass进行类加载
 * @author Songdan
 * @date 2016/11/3 9:39
 */
public class HotSwapClassloader extends ClassLoader {

    public HotSwapClassloader() {
        //把加载本类的classloader作为父类加载器，不破坏双亲委派机制从而可以访问父类加载器可以访问的类
        super(HotSwapClassloader.class.getClassLoader());
    }


    /**
     * 显式加载字节码
     * @param classByte
     * @return
     */
    public Class loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }

}
