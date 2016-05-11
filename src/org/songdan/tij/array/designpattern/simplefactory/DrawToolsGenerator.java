package org.songdan.tij.array.designpattern.simplefactory;

import com.mchange.v1.db.sql.UnsupportedTypeException;

/**
 * 充当工厂的角色
 * @author SONGDAN
 *
 */
public class DrawToolsGenerator {
    public static DrawTools getDrawTools(String type) throws UnsupportedTypeException {
        if(type!=null&&!type.isEmpty()){
            switch (type) {
                case "circle":
                    return new Circle();
                case "square":
                    return new Square();
                default:
                    throw new UnsupportedTypeException("当前"+type+"类型不支持!");
            }
        }else{
            throw new UnsupportedTypeException("类型不能为空");
        }
    }
    
    public static void main(String[] args) {
        try {
            DrawTools drawTools = DrawToolsGenerator.getDrawTools("circle");
            drawTools.draw();
            drawTools.erase();
            drawTools = DrawToolsGenerator.getDrawTools("square");
            drawTools.draw();
            drawTools.erase();
            drawTools = DrawToolsGenerator.getDrawTools("trigon");
            drawTools.draw();
            drawTools.erase();
        }
        catch (UnsupportedTypeException e) {
            e.printStackTrace();
        }
    }
}
