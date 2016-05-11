package org.songdan.tij.array.designpattern.sington;

/**
 * 单例模式 饿汉式
 * @author SONGDAN
 *
 */
public class SingletonA {

    private static final SingletonA instance = new SingletonA();
    
    private SingletonA(){
        
    }
    
    public static SingletonA getInstance(){
        return instance;
    }
    
}
