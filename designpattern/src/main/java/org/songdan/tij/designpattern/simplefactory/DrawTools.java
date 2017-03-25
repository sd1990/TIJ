package org.songdan.tij.designpattern.simplefactory;

/**
 * 绘图工具，简单工厂模式中的抽象产品类
 * @author SONGDAN
 *
 */
public abstract class DrawTools {
    /**
     * 绘图方法
     */
    public abstract void draw();
    
    /**
     * 擦除方法
     */
    public abstract void erase();
}
