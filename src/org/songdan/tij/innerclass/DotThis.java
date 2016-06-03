package org.songdan.tij.innerclass;

/**
 * 使用.this与new
 * @author SONGDAN
 *
 */
public class DotThis {
    void f(){
        System.out.println("dotthis.f()");
    }
    public class Inner{
        public DotThis outer(){
            return  DotThis.this;
        }
    }
    
    public Inner inner(){
        return new Inner();
    }
    
    public static class Inner2{
        
    }
    
    public static void main(String[] args) {
        DotThis dt = new DotThis();
        Inner inner = dt.inner();
        System.out.println(inner);
        DotThis outer = inner.outer();
        System.out.println(dt==outer);
        Inner inner2 = dt.new Inner();
        System.out.println(inner2);
        //必须先有外部类的对象
//        Inner inner3 = new Inner();
        //静态内部类与对象无关，与外部类的对象不存在依赖关系
        Inner2 inner22 = new Inner2();
    }
}
