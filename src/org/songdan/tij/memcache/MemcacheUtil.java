package org.songdan.tij.memcache;//package com.songdan.demo.memcache;
//
//import java.io.IOException;
//import java.util.concurrent.TimeoutException;
//
//import net.rubyeye.xmemcached.MemcachedClient;
//import net.rubyeye.xmemcached.XMemcachedClient;
//import net.rubyeye.xmemcached.XMemcachedClientBuilder;
//import net.rubyeye.xmemcached.exception.MemcachedException;
//import net.rubyeye.xmemcached.utils.AddrUtil;
//
//public class MemcacheUtil {
//
//    private static MemcachedClient client;
//    static {
//        try {
//            client = new XMemcachedClientBuilder(AddrUtil.getAddresses("192.168.30.213:11211")).build();
//        }
//        catch (IOException e) {
//            System.out.println("memcache client connect fail...");
//            e.printStackTrace();
//        }
//    }
//
//    public static void delete(String key) {
//        try {
//            boolean delete = client.delete(key);
//            System.out.println(key + "---delete--" + delete);
//        }
//        catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        catch (MemcachedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void set(String key,String value){
//        try {
//            client.set(key, 1000*1000, value);
//        }
//        catch (TimeoutException e) {
//        }
//        catch (InterruptedException e) {
//        }
//        catch (MemcachedException e) {
//        }
//    }
//
//    public static String get(String key){
//        String value = null;
//        try {
//            value = client.get(key);
//        }
//        catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        catch (MemcachedException e) {
//            e.printStackTrace();
//        }
//        return value;
//    }
//    public static void main(String[] args) {
//        set("name", "songdan");
//        set("value", "120kg");
//        System.out.println(get("name"));
//        System.out.println(get("value"));
//        delete("name");
//    }
//}
