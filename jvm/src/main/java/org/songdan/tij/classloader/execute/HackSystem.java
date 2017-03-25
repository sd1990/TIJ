package org.songdan.tij.classloader.execute;

import java.io.*;
import java.nio.channels.Channel;
import java.util.Properties;

/**
 * 为JavaClass劫持java.lang.System
 * 
 * @author Songdan
 * @date 2016/11/3 10:50
 */
public class HackSystem {

    public final static InputStream in = System.in;

    private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    public final static PrintStream out = new PrintStream(buffer);

    public final static PrintStream err = out;

    public static String getBufferString() {
        return buffer.toString();
    }

    public static void clearBuffer() {
        buffer.reset();
    }

    public static void setSecurityManager(final SecurityManager securityManager) {
        System.setSecurityManager(securityManager);
    }

    public static SecurityManager getSecurityManager() {
        return System.getSecurityManager();
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public static long nanoTime() {
        return System.nanoTime();
    }

    public static int identityHashCode(Object x) {
        return System.identityHashCode(x);
    }

    // 下面所有的方法都与java.lang.System的名称一样
    // 实现都是字节转调System的对应方法
    // 因版面原因，省略了其他方法
    public static void setIn(InputStream in) {
        System.setIn(in);
    }

    public static void setOut(PrintStream out) {
        System.setOut(out);
    }

    public static Console console() {
        return System.console();
    }

    public static Channel inheritedChannel() throws IOException {
        return System.inheritedChannel();
    }

    public static void setProperties(Properties props) {
        System.setProperties(props);
    }

    public static Properties getProperties() {
        return System.getProperties();
    }

    public static String setProperty(String key, String value) {

        return System.setProperty(key, value);
    }

    public static String getProperty(String key, String def) {
        return System.getProperty(key, def);
    }

    public static String clearProperty(String key) {

        return System.clearProperty(key);
    }

    public static String getenv(String name) {
        return System.getenv(name);
    }

    public static java.util.Map<String, String> getenv() {
        return System.getenv();
    }

    public static void exit(int status) {
        System.exit(status);
    }

    public static void gc() {
        System.gc();
    }

    public static void runFinalization() {
        System.runFinalization();
    }

    public static String lineSeparator() {
        return System.lineSeparator();
    }

}
