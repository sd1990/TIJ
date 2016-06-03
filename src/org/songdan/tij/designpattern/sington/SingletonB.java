package org.songdan.tij.designpattern.sington;

/**
 * 单例模式懒汉式
 * @author SONGDAN
 *
 */
public class SingletonB {
    
    private static SingletonB instance = null;
    
    private SingletonB(){
        
    }
    
    public static SingletonB getInstance(){
        if(instance == null){
            synchronized (SingletonB.class) {
                if(instance == null){
                    instance = new SingletonB();
                }
            }
        }
        return instance;
    }
    
}
