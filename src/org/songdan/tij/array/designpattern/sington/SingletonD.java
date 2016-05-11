package org.songdan.tij.array.designpattern.sington;

/**
 * 单例模式内部类实现
 * @author SONGDAN
 *
 */
public class SingletonD {
    
    private SingletonD(){
    }
    
    private static class InnerClass{
        
        private static final SingletonD instance = new SingletonD();
    }
    
    public static SingletonD getInstance(){
        return InnerClass.instance;
    }
}
