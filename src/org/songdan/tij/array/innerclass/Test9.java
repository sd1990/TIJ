package org.songdan.tij.array.innerclass;

/**
 * 匿名内部类和局部内部类
 * @author SONGDAN
 *
 */
public class Test9 {
    public interface Inter{
        void say();
    }
    
    public static class Demo{
        public Inter getInter(){
            return new Inter(){

                @Override
                public void say() {
                    System.out.println("hello world");
                }
                
            };
        }
        
        public Inter getInter2(){
            class InterImpl implements Inter{

                @Override
                public void say() {
                    System.out.println("hello 局部内部类");
                }
                
            }
            return new InterImpl();
        }
        
        public static void main(String[] args) {
            Demo demo = new Demo();
            demo.getInter().say();
            demo.getInter2().say();
        }
    }
}
