package org.songdan.tij.references;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * @author: Songdan
 * @create: 2019-06-29 10:06
 **/
public class Weak {

    /**
     * -Xms1M -Xmx1M
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        WeakReference<byte[]> reference = new WeakReference<>(new byte[1024*511]);
        System.out.println(reference.get());
        System.gc();
        System.out.println(reference.get());
        WeakHashMap<String, Object> weakHashMap = new WeakHashMap<>();
        weakHashMap.put(new String("str"), new byte[1024*256]);
        System.out.println(weakHashMap.get("str"));
        System.gc();
        System.out.println(weakHashMap.get("str"));
    }

}
